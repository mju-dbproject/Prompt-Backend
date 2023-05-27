package com.team4.prompt.user.controller;

import com.team4.prompt.common.CurrentUser;
import com.team4.prompt.user.controller.dto.*;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.service.UserPasswordService;
import com.team4.prompt.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserPasswordService userPasswordService;


    @PostMapping("/join")
    public void join(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        userService.join(userCreateRequest.newUser(passwordEncoder));
    }

    //아이디 중복체크
    @PostMapping("/check-id")
    public CheckIdResponse checkId(@RequestBody String userId) {

        boolean isNotDuplicated = userService.checkId(userId);
        return new CheckIdResponse(isNotDuplicated);
    }

    @PutMapping("/password")
    public void changePassword(@CurrentUser User user,
                               @RequestBody ChangePasswordRequest changePasswordRequest) {

        userPasswordService.changePassword(user, changePasswordRequest);
    }

    //내 정보 수정
    @PutMapping("/info")
    public void updateUserInfo(@CurrentUser User user, @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUserInfo(user, userUpdateDto);
    }

    //내 정보 조회
    @GetMapping("/info")
    public UserInfoDto getMyInfo(@CurrentUser User currentUser) {
        return userService.getMyInfo(currentUser);
    }

    //아이디찾기
    @PostMapping("/find-id")
    public String findUserId(@RequestBody FindUserIdRequest findUserIdRequest) {
        return userService.findUserIdByNameAndEmail(findUserIdRequest.getName(), findUserIdRequest.getEmail());
    }

    @PostMapping("/approval/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean approvedUser(@PathVariable String userId, @CurrentUser User user) {
        return userService.approveUser(userId);
    }
}

