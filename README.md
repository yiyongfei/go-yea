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

# 开发
## APP服务
向外部系统提供APP服务接口，服务内部实现具体的业务场景。可参照go-yea的结构建立Maven项目结构：<br>
- 公共层Common
- 模型层Model
- 业务模块层，会有多个业务模块层
- 启动器层
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
- 运行在单机模式下的Netty配置内容:
```xml
	<!-- JVM内调用 -->
	<bean id="launcherClient" class="com.yea.core.remote.client.DefaultClient" ></bean>
```
- 运行在RPC模式下的Netty配置内容:
```xml
	<!-- JVM内调用 -->
	<bean id="launcherClient" class="com.yea.core.remote.client.DefaultClient" ></bean>
	
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
	
	<!-- Netty服务端配置，Netty服务启动将在Main内调用(外部提供服务绑定端口)，启动服务后向调度中心发送服务注册请求 -->
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

* Mybatis映射文件内包含单表常规操作
	* 生成的映射文件请复制到启动器内的src/main/resources/sqlmap
	* 样例请参看go-yea/go-yea-launcher/src/main/resources/sqlmap/authorization/personinfo-sqlmap-mapping.xml
* 模型类主要有：聚合类、实体类、主键类
	* 生成的模型类请复制到Model层
	* 实体类对应单表，通过实体类完成对表进行增、改操作，
	* 聚合类默认由实体类和主键类组成，完成对表的查操作，类属性的扩充只在聚合类里扩充
	* 样例请参看go-yea/go-yea-model里的com.team.goyea.authorization.model.PersonInfo，扩充属性的对比:UserInfo
### 6、Repository以及Service
### 7、Act(重点)
APP服务对外暴露的接口，客户端请求服务端时需提供Act名，Netty服务端收到请求后根据Act名找到相应的Bean并执行
- 无事务控制Act，针对查询请求，继承com.yea.core.act.AbstractAct并实现perform方法
- AbstractAct是一个RecursiveTask，复杂业务允许拆分成多个子任务做Fork Join
```java
@Service
public class QueryOperationAct extends AbstractAct {
	protected Object perform(Object[] messages) throws Throwable {
		省略
	}
}
```
- 事务控制Act，针对增删改请求，继承com.yea.core.act.AbstractTransactionAct并实现perform方法
- AbstractTransactionAct同样是一个RecursiveTask，复杂业务允许拆分成多个子任务做Fork Join
```java
@Service
public class SaveOperationAct extends AbstractTransactionAct {
	protected Object perform(Object[] messages) throws Throwable {
		省略
	}
}
```
## WEB服务
提供Web服务，主要完成参数封装、页面展示。可参照go-yea的结构建立Maven项目结构：<br>
- 模型层Model(与APP服务共用)
- 控制层Controller
- 页面资源WEB-INFO
### 1、pom.xml
- 运行在单机模式下:
```xml
<profile>
	<!-- 单机模式 -->
	<id>standalone</id>
	<properties>
	    <profiles.active>standalone</profiles.active>
	</properties>
	<dependencies>        
		<dependency>
			<groupId>com.team</groupId>
			<artifactId>go-yea-launcher</artifactId>
			<version>0.0.1</version>
		</dependency>
    </dependencies>
</profile>
```
- 运行在RPC模式下:
```xml
<profile>
	<!-- RPC模式 -->
	<id>rpc</id>
	<properties>
	    <profiles.active>rpc</profiles.active>
	</properties>
	<dependencies>        
		<dependency>
			<groupId>com.yea</groupId>
			<artifactId>yea-dispatcher</artifactId>
		</dependency>
    </dependencies>
</profile>
```
### 2、spring-bean.xml
- 运行在单机模式下:
```xml
    <import resource="classpath:/application-launcher.xml" />
```
- 运行在RPC模式下:
```xml
    <!-- Netty编解码的Handler实现 -->
	<bean id="nettyMessageDecoder" class="com.yea.remote.netty.codec.NettyMessageDecoder"></bean>
	<bean id="nettyMessageEncoder" class="com.yea.remote.netty.codec.NettyMessageEncoder"></bean>
	
	<!-- Netty异常处理Handler实现，Netty处理时若抛出异常，由该Handler封装异常并返回调用端 -->
	<bean id="exceptionHandler" class="com.yea.remote.netty.handle.ExceptionHandler"></bean>
	
	<!-- Netty心跳检测Handler实现 -->
	<bean id="heartBeatClientHandler" class="com.yea.remote.netty.client.handle.HeartBeatClientHandler">
		<constructor-arg index="0" type="int" value="60"/>
		<constructor-arg index="1" type="int" value="60"/>
	</bean>
	
	<!-- Netty客户端收到服务端响应后的处理Handler实现 -->
	<bean id="serviceClientHandler" class="com.yea.remote.netty.client.handle.ServiceClientHandler"></bean>
	
	<!-- Netty服务处理Handler实现，所有业务操作均由该Handler处理，配置该Handle是为了支持双向调用，由服务端主动发起请求，如果发送的动作均由客户端发起，该Handle可以不配置在客户端内 -->
    <bean id="serviceServerHandler" class="com.yea.remote.netty.server.handle.ServiceServerHandler"></bean>
    
	<!-- 调度中心的配置 -->
	<bean id="zkDispatcher" class="com.yea.dispatcher.zookeeper.ZookeeperDispatcher" init-method="init">
		<property name="host" value="${zookeeper.host}" />
		<property name="port" value="${zookeeper.port}" />
	</bean>
	
	<!-- Netty客户端配置，启动时将会根据服务注册名主动连接服务端 -->
    <bean id="nettyClient" class="com.yea.remote.netty.client.NettyClient" init-method="connect" destroy-method="disconnect">
		<property name="registerName" value="${netty.server.register}" /><!-- 服务注册名 -->
		<property name="dispatcher" ref="zkDispatcher" />
		<property name="host" value="${netty.client.host}" /><!-- 该主机未设置时，系统将会读取本机IP自动设入 -->
		<property name="port" value="${netty.client.port}" /><!-- 该端口在执行start.sh脚本时，允许外部输入并替换 -->
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
	                    <ref bean="heartBeatClientHandler"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="ServiceClientHandler">
	                    <ref bean="serviceClientHandler"/>
	                </entry>
	            </map>
	            <map>
	                <entry key="ServiceServerHandler">
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
```
- Shiro的配置，请注意单机模式与RPC模式endpoint的设置(单机模式设置为DefaultClient，RPC模式设置成NettyClient):
```xml
    <bean id="sessionIdGenerator" class="org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator"/>  
	<bean id="sessionDAO" class="org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO">  
	    <property name="activeSessionsCacheName" value="shiro-activeSessionCache"/>  
	    <property name="sessionIdGenerator" ref="sessionIdGenerator"/>  
	</bean>
	
    <!-- 会话Cookie模板 -->  
	<bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
	    <constructor-arg value="JEA_SESSIONID"/>
	    <property name="httpOnly" value="true"/>
	    <property name="maxAge" value="-1"/>    <!-- maxAge=-1，表示浏览器关闭时失效此Cookie -->
	</bean>
	
	<!-- 会话管理器 -->  
	<bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">  
	    <property name="globalSessionTimeout" value="1800000"/>
	    <property name="deleteInvalidSessions" value="true"/>
	    <property name="sessionValidationSchedulerEnabled" value="true"/>
	    <property name="sessionDAO" ref="sessionDAO"/>
	    <property name="sessionIdCookieEnabled" value="true"/>
	    <property name="sessionIdCookie" ref="sessionIdCookie"/>
	</bean>
	
	<!-- rememberMe的Cookie模板 -->
	<bean id="rememberMeCookie" class="org.apache.shiro.web.servlet.SimpleCookie">  
	    <constructor-arg value="rememberMe"/>  
	    <property name="httpOnly" value="true"/>  
	    <property name="maxAge" value="604800"/><!-- 记住我的Cookie，保存时长7天 -->
	</bean>
	<!-- rememberMe管理器 -->
	<bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
	     <property name="cipherKey" value="#{T(org.apache.shiro.codec.Base64).decode('9AVvhnFLuS3KTV8KprsdAg==')}" />
         <property name="cookie" ref="rememberMeCookie"/>
	</bean>
	
    <bean id="securityManager" class="com.yea.shiro.web.mgt.WebSecurityManager">
        <property name="endpoint" ref="nettyClient"/>
        <property name="sessionManager" ref="sessionManager"/>
        <property name="rememberMeManager" ref="rememberMeManager"/>
    </bean>
    
    <!-- 相当于调用SecurityUtils.setSecurityManager(securityManager) -->
	<bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
		<property name="staticMethod" value="org.apache.shiro.SecurityUtils.setSecurityManager"/>
	    <property name="arguments" ref="securityManager"/>
	</bean>
	
	<!-- Shiro的Web过滤器 -->
	<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean" >
	    <property name="securityManager" ref="securityManager"/>
	    <property name="loginUrl" value="/login.html"/>
	    <property name="successUrl" value="/index.html"/>
	    <property name="unauthorizedUrl" value="/unauthorized.html"/>
    </bean>
    <!-- 对ShiroWeb过滤器进行包装，以初始化过滤器链（不允许延迟加载） -->
    <bean id="shiroFilterWrapper" class="com.yea.shiro.web.wrapper.ShiroFilterWrapper" init-method="init" lazy-init="false" >
	    <property name="endpoint" ref="nettyClient"/>
	    <property name="shiroFilter" ref="shiroFilter"/>
	    <property name="authenticedUrl" value="/authenticed.html"/>
	    <property name="logoutUrl" value="/logout.html"/>
    </bean>

    <!-- Shiro生命周期处理器-->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
```
### 3、spring-mvc.xml
- 配置拦截器，自动设置当前登录用户的用户信息及菜单信息
```xml
<mvc:interceptors>
    <bean class="com.yea.shiro.web.interceptor.ShiroInterceptor"></bean>
</mvc:interceptors>
```
### 4、Controller里的远程调用
- 注入NettyClient
```java
	@Autowired
	private AbstractEndpoint nettyClient;
```
- 发送请求到APP服务
```java
	CallAct act = new CallAct();
	act.setActName("queryOperationAct");
	Promise<List<OperationInfo>> promise = nettyClient.send(act);
```
- 如果发送时需要提供参数
```java
	CallAct act = new CallAct();
	act.setActName("saveOperationAct");
	Promise<List<OperationInfoPK>> promise = nettyClient.send(act, operationDto);
```
- 如果发送时需要提供多个参数
```java
	CallAct act = new CallAct();
	act.setActName("saveResourceIdentifierAct");
	Promise<ResourceIdentifierPK> promise = nettyClient.send(act, identifier, resourceId, operationId);
```
- 如果发送后要接收APP服务返回的对象
```java
	List<OperationInfo> listOperation = promise.awaitObject(10000);
```
