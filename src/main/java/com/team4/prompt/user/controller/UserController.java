package com.team4.prompt.user.controller;

import com.team4.prompt.user.controller.dto.UserCreateRequest;
import com.team4.prompt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public void join(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        userService.join(userCreateRequest.newUser(passwordEncoder));
    }
}
