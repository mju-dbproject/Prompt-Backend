package com.team4.prompt.user.service;

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


}
