#!/bin/bash

aws iam create-policy --policy-name CodeDeploy-EC2-S3 --policy-document file://CodeDeploy-EC2-S3.json
aws iam create-policy --policy-name Travis-Upload-To-S3 --policy-document file://Travis-Upload-To-S3.json
aws iam create-policy --policy-name Travis-Code-Deploy --policy-document file://Travis-Code-Deploy.json
