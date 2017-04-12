echo -n "请输入Netty服务监听端口："
read port
java -jar -server -Xms256m -Xmx256m -XX:+UseConcMarkSweepGC -XX:PermSize=64m -XX:MaxPermSize=128m -Dserver.port=$port go-yea-launcher-0.0.1.jar
