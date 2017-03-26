# neu-csye6225-4
[![Build Status](https://travis-ci.com/weili6/neu-csye6225-4.svg?token=y6k4kmm4ZbfcwpY79RLX&branch=master)](https://travis-ci.com/weili6/neu-csye6225-4)

https://travis-ci.com/weili6/neu-csye6225-4

https://neu-csye6225-spring2017-team-4.me

team4.neu-csye6225-spring2017-team-4.me  
ec2.neu-csye6225-spring2017-team-4.me  
https://neu-csye6225-spring2017-team-4.signin.aws.amazon.com/console  
## Team
- Neha Ghate ghate.n@husky.neu.edu
- Liren Huang huang.l@husky.neu.edu
- Yalin Li li.yali@husky.neu.edu
- Wei Li li.wei6@husky.neu.edu


## Technology stack

- Spring Framework
- Java
- JavaScript
- Hibernate
- MySQL
- Dynamo DB
- Image upload
- Bootstrap
- AJAX

This is a Spring boot project built with [Spring Tool Suite](https://spring.io/tools/sts/all) (STS).
You can download/clone the repo and import it as an existing project into workspace in STS.
It is a Maven project so it has all required dependencies in the pom.xml.

### Prerequisites
You need the following installed and available in your $PATH:

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache maven 3.0.3 or greater](http://maven.apache.org/install.html)

This is a simple [tutorial](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) I followed to install them.

## Scripts for setup:
Notice: Because there are a lot of configuration dependencies among AWS services, rerun any of the scripts may cause currently working AWS services to fail.

### 1. launch ec2 instance
launchEC2Instance.sh launches a Ubuntu 16.04 server and configures it with userdata.sh, then points neu-csye6225-spring2017-team-4.me to its ip.

### 2. Set up S3
setupS3.sh creates a bucket "s3.neu-csye6225-spring2017-team-4.me" on AWS S3 and if the bucket exists it gives a message "Bucket already exists". We enable versioning to the bucket and grant read permission for all users

### 3. set up web security group
setupWebSecurityGroup.sh creates a "web" security group and opens port 80 and 443 for incoming traffic from anywhere.

### 4. set up database security group
setupWebSecurityGroup.sh creates a "db" security group and opens port 3306 for incoming traffic from 'web' security group.

### 5. launch MyQql RDS instance
launchMysqlRdsInstance.sh launches a mysql instance with no public accessability.

### 6. Create Script to Create DynamoDB Table
createDynamoTable.sh creates a dynamodb table and if table exists it gives a message "already exists" .
We have table name as csye6225 with attributes personId in Number and username as String.

### 7. create IAM policies
createContinuousDeliveryIAMPolicies.sh creates three policies 'CodeDeploy-EC2-S3', 'Travis-Upload-To-S3' and 'Travis-Code-Deploy'

### 8. create travis user
createContinuousDeliveryTravisUser.sh creates a 'travis' user with 'Travis-Upload-To-S3' and 'Travis-Code-Deploy' policies attached.

### 9. create IAM roles
createContinuousDeliveryIAMRoles.sh creates a 'CodeDeployEC2ServiceRole' with 'CodeDeploy-EC2-S3' policy attached, and a 'CodeDeployServiceRole' with 'AWSCodeDeployRole' policy attched.

### 10. set up a S3 bucket for Code Deploy
setup_codedeploy_s3.sh creates a S3 bucket to deploy the application archive.

### 11. create CodeDeploy application
createContinuousDeliveryCodeDeployApp.sh creates a CodeDeploy application 'jobportal' and add the ec2 instance to it.

### 12. appspec.yml & .travis.yml
Configuration files for continuous deployment.

### 13. restartapp.sh
It's used by CodeDeploy to restart the application on the EC2 instance. It's not meant to be used by users.



## Updates

* Fix AJAX related errors, add AJAX in login.vm and register.vm;

* Upload user image to S3 and display the image on candidate profile page
