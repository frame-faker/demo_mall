package com.sanbangzi.xcx_web.api;

import com.sanbangzi.common.utils.WebResponse;
import com.sanbangzi.domain.dto.res.UserResDTO;
import com.sanbangzi.xcx_web.annotation.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/current")
    public WebResponse current(@CurrentUser UserResDTO userRes) throws Exception {
        return xcxSuccess(userRes);
    }
}
