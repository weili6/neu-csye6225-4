#!/bin/bash
#delete code deploy application

#ubuntu16.04

APP_NAME="jobportal"
aws deploy delete-application --application-name "$APP_NAME"