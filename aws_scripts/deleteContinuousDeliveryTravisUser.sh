#!/bin/bash

accesskeyid=$(aws iam list-access-keys --user-name travis | jq -r '.AccessKeyMetadata[0].AccessKeyId')

aws iam delete-access-key --access-key-id "$accesskeyid" --user-name travis
aws iam detach-user-policy --policy-arn arn:aws:iam::277964780852:policy/Travis-Upload-To-S3 --user-name travis
aws iam detach-user-policy --policy-arn arn:aws:iam::277964780852:policy/Travis-Code-Deploy --user-name travis

aws iam delete-user --user-name travis
