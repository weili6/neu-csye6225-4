#!/bin/bash

killall -q -9 java
cd /home/ubuntu
nohup java -jar team4-0.0.1-SNAPSHOT.war> mylog 2>&1 &
