package com.example.domains.service;

import com.example.domains.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static java.util.Optional.ofNullable;

@Slf4j
@Service

public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    private final CustomerService customerService;
    private final static String MAIL_MESSAGE = "Link to download file with history order for customer id:";

    public EmailService(JavaMailSender javaMailSender, CustomerService customerService) {
        this.javaMailSender = javaMailSender;
        this.customerService = customerService;
    }

    public void send(final Mail mail) {
        logger.info("\"Starting email preparation....");
        try {
            SimpleMailMessage simpleMailMessage = createMailMassage(mail);
            javaMailSender.send(simpleMailMessage);
        } catch (MailException mailException) {
            logger.error("Failed to process email sending: " + mailException.getMessage(), mailException);
        }
    }

    private SimpleMailMessage createMailMassage(final Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(mail.getMessage());
        simpleMailMessage.setTo(mail.getTo());
        return simpleMailMessage;
    }

    public void sendToCustomer(Integer customerId, String fileName) {
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/jms/" + customerId + "/downloadFile/" + fileName)
                .toUriString();
        String message = MAIL_MESSAGE + customerId + ":" + fileUrl;
        String customer = customerService.getCustomer(customerId).getMail();
        ofNullable(customer).ifPresent(consumer ->
                send(new Mail(message, consumer)));
    }
}
