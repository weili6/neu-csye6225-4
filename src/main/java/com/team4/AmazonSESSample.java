package com.team4;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class AmazonSESSample {

	static final String FROM = "mail@neu-csye6225-spring2017-team-4.me";
//    static final String FROM = "nehaghate2013@gmail.com";   // Replace with your "From" address. This address must be verified.
	//    static final String TO = "nehaghate2805@gmail.com";  // Replace with a "To" address. If your account is still in the 
                                                       // sandbox, this address must be verified.
    
    static final String SUBJECT = "Forgotten Password Reset";
    
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = "AKIAID3S7FJHI5QGZANQ";  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = "Ail5VkVuXmMPBCUzHx/WqrJgW+4y2UdOiXO1hi8GFMst";  // Replace with your SMTP password.
    
//    static final String SMTP_USERNAME = "AKIAJFBDMMPJLQAVLCIQ";  // Replace with your SMTP username.
//    static final String SMTP_PASSWORD = "ArXyIvqG52o5zNJ7CCam08hIQb7JlqyemLGhv4ZZPfEK";  // Replace with your SMTP password.
    
    // Amazon SES SMTP host name. This example uses the US East  region.
    static final String HOST = "email-smtp.us-east-1.amazonaws.com";    
    
    // The port you will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 587;

    public static void sendEmail(String TO, String tempPwd) throws Exception {

    	String body = "Your new password is "+ tempPwd+". Please change it after your login.";
        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtps");
    	props.put("mail.smtp.port", PORT); 
    	
    	// Set properties indicating that we want to use STARTTLS to encrypt the connection.
    	// The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(body,"text/plain");
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

        // Create a transport.        
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	System.out.println(transport.isConnected());
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
//            Transport.send(msg);
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();        	
        }
    }
}