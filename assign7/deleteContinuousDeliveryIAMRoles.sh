#!/bin/bash

# delete CodeDeployEC2ServiceRole instance profile
aws iam remove-role-from-instance-profile --instance-profile-name CodeDeployEC2ServiceRole --role-name CodeDeployEC2ServiceRole
aws iam delete-instance-profile --instance-profile-name CodeDeployEC2ServiceRole

# delete CodeDeployEC2ServiceRole role
aws iam detach-role-policy --role-name CodeDeployEC2ServiceRole --policy-arn arn:aws:iam::277964780852:policy/CodeDeploy-EC2-S3
aws iam delete-role --role-name CodeDeployEC2ServiceRole


# delete CodeDeployServiceRole instance profile
aws iam remove-role-from-instance-profile --instance-profile-name CodeDeployServiceRole --role-name CodeDeployServiceRole
aws iam delete-instance-profile --instance-profile-name CodeDeployServiceRole

# delete CodeDeployServiceRole role
aws iam detach-role-policy --role-name CodeDeployServiceRole --policy-arn arn:aws:iam::aws:policy/service-role/AWSCodeDeployRole
aws iam delete-role --role-name CodeDeployServiceRole
