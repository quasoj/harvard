package edu.harvard.utils.controller;

import edu.harvard.academics.entity.User;
import edu.harvard.academics.service.UserService;
import edu.harvard.utils.JwtUtils;
import edu.harvard.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result login(@RequestBody User user) {
        User result = userService.login(user);
        if (result != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", result.getId());
            claims.put("username", result.getUsername());
            claims.put("password", result.getPassword());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("username or password error!");
    }

}
