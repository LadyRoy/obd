package com.example.kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipientEmail, String subject, String text, String attachmentName, String pathToAttachment) {
        var message = mailSender.createMimeMessage();
        var file = new FileSystemResource(new File(pathToAttachment));
        var helper = (MimeMessageHelper) null;

        try {
            helper = new MimeMessageHelper(message, true);

            helper.setFrom("add.mapcejib@mail.ru");
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(attachmentName, file);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
