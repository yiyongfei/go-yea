#! /bin/sh

JAVA_OPTS="$JAVA_OPTS -server -Xms256M -Xmx256M -XX:NewRatio=2 -XX:+UseConcMarkSweepGC -XX:PermSize=64M -XX:MaxPermSize=64M"

COMMAND=""
CATALINA_PID=""
input() {
    echo -n "$COMMAND,请输入监听端口(默认端口:38080): "
    read port
    if [ ${#port} -eq 0 ]; then
        port=38080
    fi
    CATALINA_PID=launcher_$port.pid
}

status() {
    if [ ${#CATALINA_PID} -eq 0 ]; then
        input
    fi
    if [ -s "$CATALINA_PID" ]; then
        PID=`cat "$CATALINA_PID"`
        ps -p $PID >/dev/null 2>&1
        if [ $? -eq 0 ] ; then
           echo "Launcher(监听端口:$port) 运行在 PID $PID"
           ps -f -p $PID
           exit 1
        fi
    else
        echo "Launcher(监听端口:$port) 已停止服务"
    fi
}

start() {
    input
    if [ -s "$CATALINA_PID" ]; then
        PID=`cat "$CATALINA_PID"`
        ps -p $PID >/dev/null 2>&1
        if [ $? -eq 0 ] ; then
            echo "Launcher(监听端口:$port) 已运行在 PID $PID. 服务启动失败."
            ps -f -p $PID
            exit 1
        else
            echo "Removing/clearing stale PID file."
            rm -f "$CATALINA_PID" >/dev/null 2>&1
            if [ $? != 0 ]; then
                if [ -w "$CATALINA_PID" ]; then
                    cat /dev/null > "$CATALINA_PID"
                else
                    echo "PID文件存在的情况下未能清理PID文件. 服务启动失败."
                    exit 1
                fi
            fi
        fi
    fi
    
    rm -f $port.out
    nohup java -jar $JAVA_OPTS -Dserver.port=$port go-yea-launcher-0.0.1.jar > $port.out 2>&1 &
    echo $! > $CATALINA_PID
    
    status
}

stop() {
    input
    if [ -s "$CATALINA_PID" ]; then
        PID=`cat "$CATALINA_PID"`
        kill -15 $PID
        
        for k in $( seq 1 12 )
        do
            echo "正在停止服务,请稍候"
            ps -p $PID >/dev/null 2>&1
            if [ $? -ne 0 ] ; then
                break
            fi
            sleep 5
        done
        rm -f $CATALINA_PID
        
        status
    else
        echo "Launcher(监听端口:$port) 已停止服务"
    fi
}

case "$1" in
    start)
        COMMAND="Starting Launcher"
        start
    ;;
    stop)
        COMMAND="Stopping Launcher"
        stop
    ;;
    status)
        COMMAND="Launcher Status"
        status
    ;;
    *)
    echo "Usage: sh startup.sh {start|stop|status}" >&2
    exit 1
;;
esac

exit 0