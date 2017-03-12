#!/bin/bash
if [ -n "$(aws dynamodb list-tables | grep csye6225)" ]; then 
    echo "already exists" 
    exit 0
fi 

aws dynamodb create-table \
    --table-name csye6225 \
    --attribute-definitions \
    AttributeName=personId,AttributeType=N \
    AttributeName=userName,AttributeType=S \
    --key-schema AttributeName=personId,KeyType=HASH AttributeName=userName,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1


