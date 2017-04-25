### Purchase SLL certificate
PositiveSSL ($9/year) in Namecheap SSL Certificate Store

### Generate CSR(Certificate Signing Request) and RSA private key
`openssl genrsa 2048 > private-key.pem`  
`openssl req -new -key private-key.pem -out csr.pem`

Enter information that will be incorporated into the certificate request:  
Country name:US  
State of Province. Full name of state: Massachusettes  
Locality name. City name: Boston  
Organization Name. Full legal name of your company: CSYE6225  
Organizational Unit. Additional company information: Team4  
Common Name. Fully qualified domain name you need to secure: neu-csye6225-spring2017-team-4.me  
Email address. Server administrator's email address: huang.l@husky.neu.edu

### Activate the SSL certificate
In Namecheap dashboard, Submit CSR and other info. Select HTTP validation method. Download the file for domain control validation, then upload it to the root directory for the domain (by setting up a Apache server). After the process, download the certficate neu-csye6225-spring2017-team-4_me.cer (PKCS7 format).  
Note: You need only one HTTP file for all your domains and subdomains. Do not edit the file's name or contents.

### Get the CA bundle
Download the CA bundle for PositiveSSL/EssentialSSL SHA-2 from namecheap.  
CA bundle is a file that contains root and intermediate certificates. The certificate issued for your domain constitutes the certificates’ chain with a CA bundle. 


### Transform the certificate from PKCS7 to X.509
`openssl pkcs7 -print_certs -in neu-csye6225-spring2017-team-4_me.cer -out certificate.cer`

### Convert x509 certificate and Key to a pkcs12 file
`openssl pkcs12 -export -in certificate.cer -inkey private-key.pem -out server.p12 -name server -CAfile COMODO_DV_SHA-256_bundle.crt -caname root`  
-name: [alias]  
Set password: csye6225

### Convert the pkcs12 file to a java keystore
`keytool -importkeystore -deststorepass csye6225 -destkeypass csye6225 -destkeystore csye6225.jks -srckeystore server.p12 -srcstoretype PKCS12 -srcstorepass csye6225 -alias server`  
-deststorepass: [new_keystore_password]  
-destkeypass: [new_key_password]  
-destkeystore: [the a new keystore]  
-srckeystore: [the keystore from previous step]  
-srcstorepass: [the password from previous step]  
-alias: [an alias for later use]

### Spring Boot application setting
Place the keystore file csye6225.jks in the same directory as team4-0.0.1-SNAPSHOT.war.  

in src/main/resources/application.properties:

```
server.port=443
server.ssl.enabled=true
server.ssl.key-store=csye6225.jks
server.ssl.key-store-password=csye6225
server.ssl.key-alias=server
```
visit https://neu-csye6225-spring2017-team-4.me

### Might be useful: Use a self-signed certificate with Spring Boot
https://drissamri.be/blog/java/enable-https-in-spring-boot/
