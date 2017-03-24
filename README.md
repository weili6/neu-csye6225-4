# neu-csye6225-4
[![Build Status](https://travis-ci.com/weili6/neu-csye6225-4.svg?token=y6k4kmm4ZbfcwpY79RLX&branch=master)](https://travis-ci.com/weili6/neu-csye6225-4)

https://travis-ci.com/weili6/neu-csye6225-4

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
In aws_cli_scripts folder we have scripts for the following:

# 1.Create Script to Create DynamoDB Table
createDynamoTable.sh creates a dynamodb table and if table exists it gives a message "already exists" .
We have table name as csye6225 with attributes personId in Number and username as String.

# 2. Set up S3
setupS3.sh creates a bucket "s3.neu-csye6225-spring2017-team-4.me" on AWS S3 and if the bucket exists it gives a message "Bucket already exists". We enable versioning to the bucket and grant read permission for all users

