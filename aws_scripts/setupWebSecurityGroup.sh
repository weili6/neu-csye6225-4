#!/bin/bash
groupname=web

if [ -n "$(aws ec2 describe-security-groups --group-names "$groupname" 2>/dev/null)" ];then
echo $groupname already exists, delete and re-create it.
aws ec2 delete-security-group --group-name "$groupname"
fi

id=$(aws ec2 create-security-group --group-name "$groupname" --description "My web security group" --vpc-id vpc-946005f2| jq -r '.GroupId')
echo Groupid = $id

aws ec2 authorize-security-group-ingress --group-id $id --protocol tcp --port 443 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id $id --protocol tcp --port 80 --cidr 0.0.0.0/0
