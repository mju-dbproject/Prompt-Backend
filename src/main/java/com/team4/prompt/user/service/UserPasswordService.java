package com.team4.prompt.user.service;

import com.team4.prompt.user.controller.dto.ChangePasswordRequest;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userService.findByUserId(changePasswordRequest.getUserId());
        if(!changePasswordRequest.getCheckPassword().equals(changePasswordRequest.getNewPassword())){
            throw new IllegalArgumentException("비밀번호 확인이 올바르지 않습니다.");
        }
        user.updatePassword(passwordEncoder, changePasswordRequest.getNewPassword());
        userRepository.save(user);
    }


}
