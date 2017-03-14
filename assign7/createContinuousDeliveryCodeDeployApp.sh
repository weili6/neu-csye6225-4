#!/bin/bash
#create code deploy application

#ubuntu16.04

APP_NAME="jobportal"
aws deploy create-application --application-name "$APP_NAME"