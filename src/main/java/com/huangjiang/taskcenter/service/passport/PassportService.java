package com.huangjiang.taskcenter.service.passport;

import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.model.param.EmailParam;
import com.huangjiang.taskcenter.model.param.UserParam;
import com.huangjiang.taskcenter.orm.entity.MessageTemplateEntity;
import com.huangjiang.taskcenter.orm.entity.UserEntity;
import com.huangjiang.taskcenter.orm.entity.VerifyCodeEntity;
import com.huangjiang.taskcenter.orm.mapper.MessageTemplateMapper;
import com.huangjiang.taskcenter.orm.mapper.UserMapper;
import com.huangjiang.taskcenter.orm.mapper.VerifyCodeMapper;
import com.huangjiang.taskcenter.service.mail.MailService;
import com.huangjiang.taskcenter.utils.EmailUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PassportService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerifyCodeMapper verifyCodeMapper;
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Autowired
    private MailService mailService;

    public Boolean register(UserParam param) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = param.getEmail();
        if (StringUtils.isEmpty(email)) {
            throw new Exception("邮箱不能为空!");
        }
        int exist = userMapper.exist(email);
        if (exist == 0) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(param, userEntity);
            userEntity.setPassword(passwordEncoder.encode(param.getPassword()));
            String id = UUID.randomUUID().toString();
            userEntity.setId(id);
            userEntity.setStatus(Constants.USER_STATUS_NORMAL);
            return userMapper.insert(userEntity) > 0;
        } else {
            return false;
        }
    }

    public boolean getVerifyCode(String email, String purpose) throws Exception {
        MessageTemplateEntity messageTemplateEntity = messageTemplateMapper.selectByCode(purpose);
        if (messageTemplateEntity == null) {
            throw new Exception("模板不存在！");
        }
        // 生成验证码
        String verifyCCode = this.generateRandomCode();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("{code}", verifyCCode);

        // 生成邮件参数
        EmailParam emailParam = new EmailParam();
        emailParam.setSubject(messageTemplateEntity.getTitle());
        emailParam.setContent(EmailUtil.translateParams(messageTemplateEntity.getContent(), paramMap));
        emailParam.setHtmlFlag(Constants.YES.equals(messageTemplateEntity.getIsHtml()));
        List<String> to = new ArrayList<>();
        to.add(email);
        emailParam.setReceivers(to);
        mailService.sendSimpleEmail(emailParam);

        // 将验证码记录保存
        VerifyCodeEntity entity = new VerifyCodeEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setEmail(email);
        entity.setCode(verifyCCode);
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setPurpose(purpose);
        entity.setIsUsed(Constants.VERIFY_CODE_USED);
        return verifyCodeMapper.insert(entity) > 0;
    }

    public UserEntity selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    private String generateRandomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(Math.round(Math.floor(Math.random() * 10)));
        }
        return stringBuilder.toString();
    }
}
