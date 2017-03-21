#!/bin/bash

killall -q java
cd /home/ubuntu
nohup java -jar team4-0.0.1-SNAPSHOT.war &
