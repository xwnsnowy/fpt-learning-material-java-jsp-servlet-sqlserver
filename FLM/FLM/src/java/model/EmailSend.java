/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class EmailSend {
    // Send verification code for register
    public void SendVerificationCode(String email, String code)throws MessagingException, UnsupportedEncodingException{
        // dia chi email cua ban
        final String fromEmail = "swpflmg2@gmail.com";
        // Mat khai email cua ban
        final String password = "yjejimwrjkrnhohb";
        // dia chi email nguoi nhan
        final String toEmail = email;
        // mail subject 
        final String subject = "FLM verification code: " + code;
        // mail body
        final String body = "here is your flm account verification code: " + code;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);


        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "FLM-NoReply"));

        msg.setReplyTo(InternetAddress.parse(fromEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }
//    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
//       EmailSend e = new EmailSend();
//       e.SendVerificationCode("trungquan014@gmail.com", "123354234");
//    }
    
      public static String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_-+=<>?/";

        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
        SecureRandom random = new SecureRandom();

        StringBuilder passwordBuilder = new StringBuilder();

        // Add one character from each group
        passwordBuilder.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        passwordBuilder.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        passwordBuilder.append(digits.charAt(random.nextInt(digits.length())));
        passwordBuilder.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Fill the rest of the password with random characters
        int remainingLength = 8 - passwordBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            passwordBuilder.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return passwordBuilder.toString();
    }
}
