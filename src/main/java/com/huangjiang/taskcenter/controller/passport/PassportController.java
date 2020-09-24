package com.huangjiang.taskcenter.controller.passport;

import com.huangjiang.taskcenter.common.JsonResult;
import com.huangjiang.taskcenter.common.ResultCode;
import com.huangjiang.taskcenter.model.param.UserParam;
import com.huangjiang.taskcenter.service.passport.PassportService;
import com.huangjiang.taskcenter.utils.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private PassportService passportService;

    /**
     * 用户注册
     * @param param 注册参数
     * @return 注册结果
     */
    @PostMapping("/register")
    public JsonResult<Boolean> register(@RequestBody UserParam param) {
        logger.info("用户注册, param: {}", param);
        try {
            return ResultTool.success(passportService.register(param));
        } catch (Exception e) {
            logger.error("用户注册异常", e);
            return ResultTool.fail(e.getMessage());
        }
    }

    @PostMapping("/forget_password")
    public JsonResult<Boolean> forgetPassword(@RequestBody UserParam param) {
        logger.info("找回密码, param:{}", param);
        try {
            return ResultTool.success(passportService.forgetPassword(param));
        } catch (Exception e) {
            logger.error("用户找回密码异常", e);
            return ResultTool.fail(e.getMessage());
        }
    }

    /**
     * 获取验证码
     */
    @GetMapping("/get_verify_code")
    public JsonResult<Boolean> getVerifyCode(@RequestParam(name = "email") String email, @RequestParam(name = "purpose")String purpose) {
        logger.info("生成验证码,email:{}", email);
        try {
            return ResultTool.success(passportService.getVerifyCode(email, purpose));
        } catch (Exception e) {
            logger.error("生成验证码异常", e);
            return ResultTool.fail(ResultCode.GENERATE_VERIFY_CODE_ERROR);
        }
    }
}
