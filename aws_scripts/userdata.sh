#!/bin/bash
ln -sf /usr/share/zoneinfo/Etc/UTC /etc/localtime
echo "Etc/UTC" > /etc/timezone
dpkg-reconfigure -f noninteractive tzdata

apt-get update
apt-get install openjdk-8-jre-headless python-pip ruby wget gdebi-core -y
cd /home/ubuntu
wget https://aws-codedeploy-us-east-1.s3.amazonaws.com/latest/install
chmod +x ./install
./install auto
systemctl start codedeploy-agent
