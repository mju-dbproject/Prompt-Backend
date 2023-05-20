package com.team4.prompt.user.service;

import com.team4.prompt.user.controller.dto.UserInfoDto;
import com.team4.prompt.user.controller.dto.UserUpdateDto;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public void join(User user) {
        Integer enteringOrderInYear = userRepository.findEnteringCountInYear(LocalDateTime.now());
        System.out.println(enteringOrderInYear);
        user.giveEmployeeNumber(enteringOrderInYear);
        userRepository.save(user);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
    }
    public boolean checkId(String userId) {
        return userRepository.findByUserId(userId).isEmpty();
    }
    public void updateUserInfo(String employeeNumber, UserUpdateDto userUpdateDto) {
        User user = userRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다."));

        userUpdateDto.name().ifPresent(user::updateName);
        userUpdateDto.email().ifPresent(user::updateEmail);
        userUpdateDto.phoneNumber().ifPresent(user::updatePhoneNumber);
        userUpdateDto.skill().ifPresent(user::updateSkill);

        userRepository.save(user);
    }

    //public void changePassword(String userId, String checkPassword, String newPassword) {
    //    User user = userRepository.findByUserId(userId)
    //            .orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다."));

    //    if (!user.matchPassword(passwordEncoder, checkPassword)) {
            //예외처리
    //    }

    //    user.updatePassword(passwordEncoder, newPassword);
    //    userRepository.save(user);

    //}

    public UserInfoDto getMyInfo(String employeeNumber) {
        User user = userRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다."));

        return new UserInfoDto(user);
    }


}





