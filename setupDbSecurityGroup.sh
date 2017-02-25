#!/bin/bash
groupname=db

if [ -n "$(aws ec2 describe-security-groups --group-names "$groupname" 2>/dev/null)" ];then
echo $groupname already exists, delete and re-create it.
aws ec2 delete-security-group --group-name "$groupname"
fi

id=$(aws ec2 create-security-group --group-name "$groupname" --description "My database security group" --vpc-id vpc-946005f2| jq -r '.SecurityGroups[0].GroupId')
echo Groupid = $id

sourceGroupId=$(aws ec2 describe-security-groups --group-names "web"| jq -r '.SecurityGroups[0].GroupId')

aws ec2 authorize-security-group-ingress --group-id $id --protocol tcp --port 3306 --source-group $sourceGroupId