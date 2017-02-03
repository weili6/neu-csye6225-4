# neu-csye6225-4
![Travis status] (https://travis-ci.com/weili6/neu-csye6225-4.svg?token=K8QfMubRUxdNYz43egWp&branch=master)  
[deployed demo](http://ec2-52-33-87-139.us-west-2.compute.amazonaws.com:8080/demo/)  
build  
`$mvn package`  
run locally  
`java -jar target/demo-xxx.war`  
## Team
- Neha Ghate ghate.n@husky.neu.edu
- Liren Huang huang.l@husky.neu.edu
- Yalin Li li.yali@husky.neu.edu
- Wei Li li.wei6@husky.neu.edu


## Design document for web application
_Rent a parking spot_:  
Marketplace for people to list, discover, and book unique parking spots, especially when you are new in town.  
To make it the easiest way for people to monetize their parking space and showcase it to an audience of millions.


Users
- One wanting to rent a spot
- One searching for parking spot
- Admin

Use cases

1. User Registration
2. User can rent a private parking spot
3. User can search for nearest parking spot
_use Google Maps API to display nearby available parking spots based on user's current locations_
4. Admin authorizes the legit users publishing the parking spot.

## Technology stack

- Spring Framework
- Java
- Python
- MySQL
- Dynamo DB
- React.js
- Data exchange using JSON
- Bootstrap
- Jquery AJAX
