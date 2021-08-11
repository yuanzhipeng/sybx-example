package cc.sybx.example.app.controller;

import cc.sybx.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yuanzp
 * @Date : 2021/8/11 下午11:34
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("count")
    public String count(){
        return userService.count();
    }
}
