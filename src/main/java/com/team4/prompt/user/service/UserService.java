package com.team4.prompt.user.service;

import com.team4.prompt.user.controller.dto.UserInfoDto;
import com.team4.prompt.user.controller.dto.UserUpdateDto;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void join(User user) {
        int enteringOrderInYear = userRepository.findEnteringCountInYear(LocalDateTime.now());
        user.giveEmployeeNumber(enteringOrderInYear);
        userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(""));
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

    public UserInfoDto getMyInfo(String employeeNumber) {
        User user = userRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다."));

        return new UserInfoDto(user);
    }

    public String findUserIdByNameAndEmail(String name, String email) {
        User user = userRepository.findByNameAndEmail(name, email)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return user.getUserId();
    }

}
