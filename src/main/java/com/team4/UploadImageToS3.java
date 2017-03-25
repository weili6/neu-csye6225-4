package com.team4;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class UploadImageToS3 {
	private static String bucketName     = "s3.neu-csye6225-spring2017-team-4.me";
	private static String access_key = "AKIAI5AZ7JKG5LUOZDSA";
	private static String secret_key = "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";

	
	public void uploadImages(File file, String username) throws IOException {
		BasicAWSCredentials creds = new BasicAWSCredentials(access_key, secret_key); 
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).build();
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            
            s3client.putObject(new PutObjectRequest(
            		                 bucketName, "images/"+username, file));

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
	}

}
