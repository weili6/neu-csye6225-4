#!/bin/bash
# Setup Amazon S3

#ubuntu16.04
MY_BUCKET = "s3.neu-csye6225-spring2017-team-4.me"

if[aws s3api head-bucket --bucket "$MY_BUCKET" | grep -q "Not Found"]
	then
	aws s3api create-bucket --bucket "$MY_BUCKET" --region us-east-1
else
	echo "Bucket already exists"
	exit
fi

aws s3api put-bucket-versioning --bucket "$MY_BUCKET" --versioning-configuration Status=Enabled

aws s3api put-bucket-tagging --bucket "$MY_BUCKET" --tagging 'TagSet=[{Key=TEAM,Value=4}]'

aws s3api put-bucket-acl --bucket "$MY_BUCKET" --grant-read uri=http://acs.amazonaws.com/groups/global/AllUsers