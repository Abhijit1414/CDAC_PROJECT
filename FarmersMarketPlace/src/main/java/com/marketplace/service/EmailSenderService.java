package com.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    // Send a simple email without attachment
    public void sendSimpleEmail(String toEmail, String body, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("gcp991@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
            System.out.println("Mail sent successfully...");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error sending email: " + ex.getMessage());
        }
    }

    // Send email with attachment (like a PDF)
    public boolean sendEmailWithAttachment(String toEmail, String body, String subject, String attachment)
            throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("gcp991@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            // Attach the file
            FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
            mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);

            mailSender.send(mimeMessage);
            System.out.println("Mail with attachment sent successfully...");
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw new MessagingException("Error sending email with attachment: " + ex.getMessage());
        }
    }
}

