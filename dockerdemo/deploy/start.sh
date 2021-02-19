#!/bin/sh

ulimit -v unlimited
ulimit -m unlimited
ulimit -c unlimited
ulimit -n 65536
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./

SERVICE_DIR=$(cd `dirname $0`; pwd)
SERVICE_NAME=aimanager

#配置skywallking
#export SW_AGENT_NAME=$SERVICE_NAME
#SKYWALLKING_AGENT="-javaagent:../apache-skywalking-apm-bin-es7/agent/skywalking-agent.jar"

#配置注册中
#REGISTER_ARGS="--spring.cloud.zookeeper.enabled=true --spring.cloud.zookeeper.connect-string=172.31.3.83:2181,172.31.3.83:2182,172.31.3.83:2183"

#JVM参数
JVM_ARGS="-Xms2g -Xmx2g"
#JVM_ARGS="-Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:gc.log"

./stop.sh
sleep 3
v=`grep server.port application.properties|cut -d'=' -f2`
port=$(echo $v | sed 's/\r//')
echo ">>> check port $port"
grep_port=`netstat -tlpn | grep "\b$port\b"`
echo ">>> grep port is $grep_port"
if [ -n "$grep_port" ]; then
  echo ">>> port $port is in use"
  echo ">>> start failure!"
  exit 1
else
  echo ">>> port $port is not in use"
fi
procedure=`ps -ef | grep -w "${SERVICE_DIR}/${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
if [ "${procedure}" = "" ]; then
  echo ">>> start ${SERVICE_NAME}..."
  exec nohup java ${JVM_ARGS} -jar ${SERVICE_DIR}/${SERVICE_NAME}\.jar >/dev/null 2>&1 &
  echo ">>> pid $!"
  echo ">>> start success!"
else
  echo ">>> ${SERVICE_NAME} is start"
fi