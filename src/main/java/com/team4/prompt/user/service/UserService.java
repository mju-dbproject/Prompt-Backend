package com.team4.prompt.user.service;

import com.team4.prompt.user.controller.dto.ApproveListResponse;
import com.team4.prompt.user.controller.dto.ApproveUserDto;
import com.team4.prompt.user.controller.dto.UserInfoDto;
import com.team4.prompt.user.controller.dto.UserUpdateDto;
import com.team4.prompt.user.model.Role;
import com.team4.prompt.user.model.User;
import com.team4.prompt.user.repository.UserRepository;
import java.time.LocalDateTime;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
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

    @Transactional
    public void updateUserInfo(User currentUser, UserUpdateDto userUpdateDto) {
        String userId = currentUser.getUserId();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));

        userUpdateDto.name().ifPresent(user::updateName);
        userUpdateDto.email().ifPresent(user::updateEmail);
        userUpdateDto.phoneNumber().ifPresent(user::updatePhoneNumber);
        userUpdateDto.skill().ifPresent(user::updateSkill);

        userRepository.save(user);
    }

    public UserInfoDto getMyInfo(User currentUser) {
        String userId = currentUser.getUserId();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));

        return new UserInfoDto(user);
    }

    public String findUserIdByNameAndRegisterNumber(String name, String registerNumber) {
        User user = userRepository.findByNameAndRegisterNumber(name, registerNumber)
                .orElseThrow(() -> new IllegalArgumentException(""));

        return user.getUserId();
    }

    @Transactional
    public void approveUser(Long approveId) {
        User user = findUserById(approveId);
        if(user.isApproved()){
            throw new IllegalArgumentException("이미 승인 처리가 된 회원입니다.");
        }
        user.setApproved(true);
        user.approveEmployee(Role.USER);
        userRepository.save(user);
    }

    @Transactional
    public void rejectUser(Long approveId) {
        User user = findUserById(approveId);
        if(user.isApproved()){
            throw new IllegalArgumentException("이미 승인 처리가 된 회원입니다.");
        }
        userRepository.delete(user);
    }

    public ApproveListResponse getApprovalList() {
        List<User> waitingUsers = userRepository.findByRole(Role.WAITING);
        return new ApproveListResponse(waitingUsers.stream().map(user -> new ApproveUserDto(user.getId(), user.getName())).toList());
    }
}
