#!/bin/bash

aws iam delete-policy --policy-arn arn:aws:iam::277964780852:policy/CodeDeploy-EC2-S3
aws iam delete-policy --policy-arn arn:aws:iam::277964780852:policy/Travis-Upload-To-S3
aws iam delete-policy --policy-arn arn:aws:iam::277964780852:policy/Travis-Code-Deploy
