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
- 浏览器输入http://localhost:port/go-yea-web/
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
- 浏览器输入http://localhost:port/go-yea-web/
- 登录用户:admin
- 登录密码:admin
### 注意
RPC模式下，启动Web服务时会调用APP服务初始化Shiro权限管理信息，启动顺序先APP后WEB。实际应用中可单独部署一台APP用于提供统一认证授权服务。

# 开发开始
## APP服务
### 1、建立通用Dao类
- 请参看go-yea/go-yea-common里的com.team.goyea.common.dao.CommonDao
```java
@Repository
public class CommonDao<T> extends AbstractBaseDAO<T> {
省略
}
```
### 2、建立Model层
- Model层将存放各模块所需要的模型类，它们将会被APP服务和WEB服务共同使用
### 3、建立模块对应的Maven Module Project
- 每个模块原则上允许单独部署，它的部署将依赖于Model层以及部署launcher里的sqlmap-mapping.xml
- 同JVM各模块之间的调用将通过com.yea.core.remote.client.DefaultClient来完成
- 模块请参看权限模块go-yea/go-yea-permission和授权模块go-yea/go-yea-authorization
### 4、建立启动器
- 每个单独部署的APP服务均需要有个启动器，启动器内将存放Main类以及该APP服务所涉及的DB操作
- 启动器参看go-yea/go-yea-launcher
### 5、生成Mybatis的映射文件及相应的Domain

* 映射文件内包含单表常规操作
  * 生成的映射文件请复制到启动器内的src/main/resources/sqlmap
  * 请参看go-yea/go-yea-launcher/src/main/resources/sqlmap/authorization/personinfo-sqlmap-mapping.xml
- 生成的类主要有：聚合类、实体类、主键类，实体类对应单表，通过实体类完成对表进行增、改操作，而聚合类默认由实体类和主键类组成，完成对表的查操作，类属性的扩充只在聚合类里扩充(请参看go-yea/go-yea-model里的com.team.goyea.authorization.model.PersonInfo，扩充属性的对比:UserInfo)
- Dao类可以不生成，使用公共Dao类便于后续将DB操作单独封装成服务提供调用
