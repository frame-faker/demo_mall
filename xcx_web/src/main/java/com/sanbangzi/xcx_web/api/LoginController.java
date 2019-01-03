package com.sanbangzi.xcx_web.api;

import com.sanbangzi.common.utils.WebResponse;
import com.sanbangzi.user_service_api.api.XcxLoginService;
import com.sanbangzi.xcx_web.annotation.IgnoreLogin;
import com.sanbangzi.xcx_web.annotation.RequestParamsNotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private XcxLoginService xcxLoginService;

    @IgnoreLogin
    @RequestParamsNotEmpty(names = {"code"})
    @GetMapping("/login")
    public WebResponse login(String code) throws Exception {
        return xcxSuccess(xcxLoginService.login(code));
    }

    @IgnoreLogin
    @RequestParamsNotEmpty(names = {"code", "userInfo"})
    @GetMapping("/register")
    public WebResponse register(String code, String userInfo) throws Exception {
        return xcxSuccess(xcxLoginService.register(code, userInfo));
    }
}
