package com.huangjiang.taskcenter.service.mail;

import com.huangjiang.taskcenter.model.param.EmailParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service
public class MailService {

    @Resource
    private JavaMailSender mailSender;

    // 从配置文件中注入发件人的姓名
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleEmail(EmailParam emailParam) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 发件人
        message.setTo(emailParam.getReceivers().toArray(new String[]{}));
        if (!CollectionUtils.isEmpty(emailParam.getCc())) {
            message.setCc(emailParam.getCc().toArray(new String[]{}));
        }
        message.setSubject(emailParam.getSubject());
        message.setText(emailParam.getContent());
        mailSender.send(message);
    }
}
