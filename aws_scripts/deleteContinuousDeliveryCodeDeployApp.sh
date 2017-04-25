#!/bin/bash
#delete code deploy application

APP_NAME="jobportal"
aws deploy delete-application --application-name "$APP_NAME"
