# go-yea

# 启动

## 单机模式
### 1、准备数据库
- 建表：Postgresql，请执行go-yea/go-yea-launcher/src/main/sql/ddl_sql.sql；Mysql，请执行yea库里的yea/yea-shiro/src/sql/shiro_db.mwb（MysqlWorkbench打开）所生成的脚本。
- 初始化数据：请执行go-yea/go-yea-launcher/src/main/sql/init_sql.sql
### 2、Maven打包
执行go-yea/pom.xml：
```bash
mvn clean package -Dmaven.test.skip=true -Pdevelop
```
### 3、部署Web服务
复制go-yea-web/target/go-yea-web.war到应用服务器部署目录内，启动服务器
### 4、登录访问
- 浏览器输入http://host:port/go-yea-web/
- 登录用户:admin
- 登录密码:admin

## RPC模式
### 1、准备数据库
参见单机模式
### 2、安装zookeeper
### 3、Maven打包
执行go-yea/pom.xml：
```bash
mvn clean package -Dmaven.test.skip=true
```
### 4、启动APP服务
复制go-yea/go-yea-launcher/target/go-yea-launcher-0.0.1-release.tar.gz到执行目录
```bash
tar -xzvf go-yea-launcher-0.0.1-release.tar.gz;
cd launcher;
sh start.sh;
```
启动时会要求输入Netty服务端绑定端口，输入端口或直接回车
### 5、部署Web服务
复制go-yea-web/target/go-yea-web.war到应用服务器部署目录内，启动服务器
### 6、登录访问
- 浏览器输入http://host:port/go-yea-web/
- 登录用户:admin
- 登录密码:admin
### 注意
RPC模式下，启动Web服务时会调用APP服务初始化Shiro权限管理信息，启动顺序先APP后WEB。实际应用中可单独部署一台APP用于提供统一认证授权服务。

# 开发开始
## APP服务
### 1、建立通用Dao类
```java
@Repository
public class CommonDao<T> extends AbstractBaseDAO<T> {
  省略
}
```
- 通用Dao请参看go-yea/go-yea-common里的com.team.goyea.common.dao.CommonDao
- 若不使用通用Dao，则各业务所产生的Dao类请放置各个模块对应的Maven Module Project内
### 2、建立Model层
- Model层将存放各模块所需要的模型类，它们将会被APP服务和WEB服务共同使用
### 3、建立模块对应的Maven Module Project
- 模块允许单独部署成APP服务，便于未来拆分服务
- 模块间调用分二种场景，非JVM内的将通过RPC完成，而JVM的调用将通过com.yea.core.remote.client.DefaultClient来完成
- 模块请参看权限模块go-yea/go-yea-permission和授权模块go-yea/go-yea-authorization
### 4、建立启动器
- 每个单独部署的APP服务均需要启动器，启动器内将存放Main类、该APP服务所涉及的DB操作以及启动APP服务所需的Spring配置文件
- 启动器提供Netty服务端配置、DB配置，同时基于APP服务的实际调用情况酌情提供Netty客户端配置
- 启动器请参看go-yea/go-yea-launcher
- 配置内容:
```xml

	<!-- Netty编解码的Handler实现 -->
  <bean id="nettyMessageDecoder" class="com.yea.remote.netty.codec.NettyMessageDecoder"></bean>
	<bean id="nettyMessageEncoder" class="com.yea.remote.netty.codec.NettyMessageEncoder"></bean>
	
	<!-- Netty心跳检测Handler实现 -->
	<bean id="heartBeatServerHandler" class="com.yea.remote.netty.server.handle.HeartBeatServerHandler">
		<constructor-arg index="0" type="int" value="60"/>
		<constructor-arg index="1" type="int" value="60"/>
	</bean>
	
	<!-- Netty服务处理Handler实现，所有业务操作均由该Handler处理 -->
	<bean id="serviceServerHandler" class="com.yea.remote.netty.server.handle.ServiceServerHandler"></bean>
	
  <!-- Netty异常处理Handler实现，Netty处理时若抛出异常，由该Handler封装异常并返回调用端 -->
	<bean id="exceptionHandler" class="com.yea.remote.netty.handle.ExceptionHandler"></bean>
	
	<!-- Zookeeper调度中心的配置 -->
	<bean id="zkDispatcher" class="com.yea.dispatcher.zookeeper.ZookeeperDispatcher" init-method="init">
		<property name="host" value="${zookeeper.host}" />
		<property name="port" value="${zookeeper.port}" />
	</bean>
	
  <!-- Netty服务端配置，Netty服务启动将在Main内调用(外部提供服务绑定端口) -->
	<bean id="nettyServer" class="com.yea.remote.netty.server.NettyServer" destroy-method="shutdown">
		<property name="registerName" value="${netty.server.register}" /><!-- 服务注册名，将在Zookeeper内注册 -->
		<property name="dispatcher" ref="zkDispatcher" />
		<property name="host" value="${netty.server.host}" /><!-- 该主机未设置时，系统将会读取本机IP自动设入 -->
		<property name="port" value="${netty.server.port}" /><!-- 该端口在执行start.sh脚本时，允许外部输入并替换 -->
	    <property name="listHandler">
            <list>
                <map>
	                <entry key="MessageDecoder">
	                    <ref bean="nettyMessageDecoder"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="MessageEncoder">
	                    <ref bean="nettyMessageEncoder"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="HeartBeatHandler">
	                    <ref bean="heartBeatServerHandler"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="ServiceHandler">
	                    <ref bean="serviceServerHandler"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="ExceptionHandler">
	                    <ref bean="exceptionHandler"/>
	                </entry>
	            </map>
            </list>
        </property>
	</bean>
	<!-- Netty服务端的配置 End -->
```
### 5、生成Mybatis的映射文件及相应的Domain

* 映射文件内包含单表常规操作
    * 生成的映射文件请复制到启动器内的src/main/resources/sqlmap
    * 请参看go-yea/go-yea-launcher/src/main/resources/sqlmap/authorization/personinfo-sqlmap-mapping.xml
- 生成的类主要有：聚合类、实体类、主键类，实体类对应单表，通过实体类完成对表进行增、改操作，而聚合类默认由实体类和主键类组成，完成对表的查操作，类属性的扩充只在聚合类里扩充(请参看go-yea/go-yea-model里的com.team.goyea.authorization.model.PersonInfo，扩充属性的对比:UserInfo)
### 6、Repository以及Service
### 7、Act
Nett
- 无事务控制Act

