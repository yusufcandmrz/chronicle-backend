package com.yusufcandmrz.chronicle.controller.impl;

import com.yusufcandmrz.chronicle.controller.UserController;
import com.yusufcandmrz.chronicle.dto.ReadCurrentUserResponse;
import com.yusufcandmrz.chronicle.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chronicle/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/me")
    public ReadCurrentUserResponse readCurrentUser() {
        return userService.readCurrentUser();
    }
}
