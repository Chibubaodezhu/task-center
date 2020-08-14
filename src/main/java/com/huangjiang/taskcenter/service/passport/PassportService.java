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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PassportService {

    private Logger logger = LoggerFactory.getLogger(PassportService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerifyCodeMapper verifyCodeMapper;
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Autowired
    private MailService mailService;

    @Transactional
    public Boolean register(UserParam param) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = param.getEmail();
        if (StringUtils.isEmpty(email)) {
            throw new Exception("邮箱不能为空!");
        }
        VerifyCodeEntity verifyCodeEntity = verifyCodeMapper.selectByMail(email);
        if (verifyCodeEntity == null) {
            throw new Exception("请先获取验证码");
        }
        if (!verifyCodeEntity.getCode().equals(param.getVerificationCode())) {
            throw new Exception("验证码不正确");
        }
        long time = verifyCodeEntity.getCreatedAt();
        long now = System.currentTimeMillis();
        if ((now - time) > (5 * 60 * 1000)) {
            throw new Exception("验证码已失效，请重新获取");
        }
        int exist = userMapper.exist(email);
        if (exist == 0) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(param, userEntity);
            userEntity.setPassword(passwordEncoder.encode(param.getPassword()));
            String id = UUID.randomUUID().toString();
            userEntity.setId(id);
            userEntity.setStatus(Constants.USER_STATUS_NORMAL);
            int count = verifyCodeMapper.updateStatus(verifyCodeEntity.getId());
            if (count <= 0) {
                logger.error("更新验证码状态失败");
            }
            return userMapper.insert(userEntity) > 0;
        } else {
            throw new Exception("用户已注册，请尝试找回密码");
        }
    }

    public boolean getVerifyCode(String email, String purpose) throws Exception {
        MessageTemplateEntity messageTemplateEntity = messageTemplateMapper.selectByCode(purpose);
        if (messageTemplateEntity == null) {
            throw new Exception("模板不存在！");
        }
        // 生成验证码
        String verifyCode = this.generateRandomCode();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("{verifyCode}", verifyCode);

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
        entity.setCode(verifyCode);
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
