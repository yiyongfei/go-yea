# go-yea

# 快速开始
## 1、准备数据库
>建表：Postgresql，请执行go-yea-launcher/src/main/sql/ddl_sql.sql；Mysql，请执行yea库里的yea-shiro/src/sql/shiro_db.mwb（MysqlWorkbench打开）所生成的脚本。
>初始化数据：请执行go-yea-launcher/src/main/sql/init_sql.sql
## 2、Maven打包
进目录：go-yea，执行：
```bash
mvn clean package -Dmaven.test.skip=true -Pdevelop
```
## 3、部署War包并启动
复制go-yea-web/target/go-yea-web.war到应用服务器部署目录内，启动服务器

## 4、登录访问
>浏览器输入http://localhost:port/go-yea-web/
>登录用户:admin
>登录密码:admin
