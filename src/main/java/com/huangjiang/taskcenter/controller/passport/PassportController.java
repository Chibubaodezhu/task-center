package com.huangjiang.taskcenter.controller.passport;

import com.huangjiang.taskcenter.common.JsonResult;
import com.huangjiang.taskcenter.model.param.UserParam;
import com.huangjiang.taskcenter.service.passport.PassportService;
import com.huangjiang.taskcenter.utils.ResultTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private PassportService passportService;

    @PostMapping("/register")
    public JsonResult<Boolean> register(@RequestBody UserParam param) throws Exception {
        logger.info("用户注册, param: {}", param);
        return ResultTool.success(passportService.register(param));
    }
}
