#!/bin/bash

# CodeDeployEC2ServiceRole role
aws iam create-role --role-name CodeDeployEC2ServiceRole --assume-role-policy-document file://trustpolicyforec2.json
aws iam attach-role-policy --policy-arn arn:aws:iam::277964780852:policy/CodeDeploy-EC2-S3 --role-name CodeDeployEC2ServiceRole

# CodeDeployEC2ServiceRole instance profile
aws iam create-instance-profile --instance-profile-name CodeDeployEC2ServiceRole
aws iam add-role-to-instance-profile --instance-profile-name CodeDeployEC2ServiceRole --role-name CodeDeployEC2ServiceRole


# CodeDeployServiceRole role
aws iam create-role --role-name CodeDeployServiceRole --assume-role-policy-document file://trustpolicycodedeploy.json
aws iam attach-role-policy --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole --role-name CodeDeployServiceRole

# CodeDeployServiceRole instance profile
aws iam create-instance-profile --instance-profile-name CodeDeployServiceRole
aws iam add-role-to-instance-profile --instance-profile-name CodeDeployServiceRole --role-name CodeDeployServiceRole
