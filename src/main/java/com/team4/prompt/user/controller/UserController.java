package com.team4.prompt.user.controller;


import com.team4.prompt.user.controller.dto.UserCreateRequest;
import com.team4.prompt.user.controller.dto.UserInfoDto;
import com.team4.prompt.user.controller.dto.UserUpdateDto;
import com.team4.prompt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    //아이디 중복체크
    @GetMapping("/check-id")
    public boolean checkId(@RequestParam String userId) {
        return userService.checkId(userId);  //true: 중복없음
    }

    //@PutMapping("/password")
    //public void changePassword(String userId,
    //                           @RequestParam("checkPassword") String checkPassword,
    //                           @RequestParam("newPassword") String newPassword) {

    //    userServiceImpl.changePassword(userId, checkPassword, newPassword);
    //}

    @PutMapping("/info/{employeeNumber}")
    public void updateUserInfo(@PathVariable String employeeNumber, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUserInfo(employeeNumber, userUpdateDto);
    }

    @GetMapping("/info/{employeeNumber}")
    public UserInfoDto getMyInfo(@PathVariable String employeeNumber) {
        return userService.getMyInfo(employeeNumber);
    }
}
