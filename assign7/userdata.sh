#!/bin/bash
apt-get update
apt-get install tomcat8 python-pip ruby wget gdebi-core -y
cd /home/ubuntu
wget https://aws-codedeploy-us-east-1.s3.amazonaws.com/latest/install
chmod +x ./install
./install auto
systemctl start codedeploy-agent
