#!/bin/sh
SERVICE_DIR=$(cd `dirname $0`; pwd)
SERVICE_NAME=aimanager

procedure=`ps -ef | grep -w "${SERVICE_DIR}/${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
if [ "${procedure}" = "" ]; then
  echo ">>> ${SERVICE_NAME} is stop"
else
  kill -9 ${procedure}
  sleep 1
  argprocedure=`ps -ef | grep -w "${SERVICE_DIR}/${SERVICE_NAME}" |grep -w "java"| grep -v "grep" | awk '{print $2}'`
  if [ "${argprocedure}" = "" ]; then
	  echo ">>> ${SERVICE_NAME} stop success"
  else
    kill -9 ${argprocedure}
    echo ">>> ${SERVICE_NAME} stop error"
  fi
fi