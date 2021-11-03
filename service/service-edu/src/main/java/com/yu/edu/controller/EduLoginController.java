package com.yu.edu.controller;

import com.yu.commonUtils.R;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 19:28 2021/11/1
 * @Modified By:
 */
@Api(value = "讲师管理")
@RestController
@RequestMapping("edu/user")
@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login() {

        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info()
    {

        return R.ok().data("roles","[admin]")
                     .data("name","admin")
                     .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
