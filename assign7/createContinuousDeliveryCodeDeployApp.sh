#!/bin/bash
#create code deploy application

APP_NAME="jobportal"
aws deploy create-application --application-name "$APP_NAME"

aws deploy create-deployment-group \
    --application-name "$APP_NAME" \
    --deployment-config-name CodeDeployDefault.OneAtATime \
    --deployment-group-name "$APP_NAME" \
    --deployment-style deploymentType=IN_PLACE,deploymentOption=WITHOUT_TRAFFIC_CONTROL \
    --ec2-tag-filters Key=stack,Value=Production,Type=KEY_AND_VALUE \
    --service-role-arn arn:aws:iam::277964780852:role/CodeDeployServiceRole
