package com.example.let__cook;

import android.content.Context;
import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    public static void sendEmail(Context context, String toEmail, String subject, String messageBody) {
        new Thread(() -> {
            try {
                final String fromEmail = "23050084@student.bdu.edu.vn";
                final String password = "jqmt fvrp brpe gjpv";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(fromEmail, password);
                            }
                        });
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject(subject);
                message.setText(messageBody);
                Transport.send(message);
            } catch (Exception e) {
                Log.e("EmailSender", "Error sending email: " + e.getMessage());
            }
        }).start();
    }
}