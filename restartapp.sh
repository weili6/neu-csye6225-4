#!/bin/bash

service awslogs restart

killall -q -9 java
cd /home/ec2-user
nohup java -jar team4-0.0.1-SNAPSHOT.war> mylog 2>&1 &
