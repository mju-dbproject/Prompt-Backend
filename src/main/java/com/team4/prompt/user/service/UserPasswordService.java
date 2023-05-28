package com.team4.prompt.user.service;

import com.team4.prompt.user.controller.dto.ChangePasswordRequest;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void changePassword(User currentUser, ChangePasswordRequest changePasswordRequest) {
        String userId = currentUser.getUserId();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
        if (!passwordEncoder.matches(changePasswordRequest.getCheckPassword(), user.getPassword())) {
            throw new IllegalArgumentException("");
        }
        user.updatePassword(passwordEncoder, changePasswordRequest.getNewPassword());
        userRepository.save(user);

    }
}
