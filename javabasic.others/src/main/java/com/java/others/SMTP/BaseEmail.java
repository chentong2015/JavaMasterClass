package com.java.others.SMTP;

import java.util.Properties;

// SMTP：文件传输协议
// Javax.mail.jar: The JavaMail reference implementation, including the SMTP, IMAP, and POP3 protocol providers
public class BaseEmail {

    private void testSendEmail() {
        // 创建Properties类对象, 提供一组属性信息, 包含属性列表(键，值)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");

        // Username: SMTP账户的登录名称
        // Password: 不是邮箱账户的登录密码，而是应用密码，需要登录邮箱，在设置中开启此应用
        /*
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 异常处理 javax.mail.AuthenticationFailedException: 550 User has no permission
                return new PasswordAuthentication("user name", "password");
            }
        });

        try {
            Message msg = new MimeMessage(session);
            // 设置发送邮件基本信息
            Address fromAddress = new InternetAddress("sender@gmail.com");
            msg.setFrom(fromAddress);
            Address[] sendAddress = InternetAddress.parse("receiver@gmail.com", false);
            msg.setRecipients(Message.RecipientType.TO, sendAddress);
            msg.setSubject("Email Subject");
            msg.setText("The is a test");
            msg.setHeader("Header", "Sub-header");
            msg.setSentDate(new Date());

            // 添加邮件附件
            // MimeBodyPart messageBodyPart = new MimeBodyPart();
            // Multipart multipart = new MimeMultipart();
            // DataSource source = new FileDataSource("file path");
            // messageBodyPart.setDataHandler(new DataHandler(source));
            // messageBodyPart.setFileName("file name");
            // multipart.addBodyPart(messageBodyPart);
            // msg.setContent(multipart);

            // 提供身份认证，使用SMTPTransport发送
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "sender@gmail.com", "password");
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Response: " + transport.getLastServerResponse());
            transport.close();
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
        */
    }
}
