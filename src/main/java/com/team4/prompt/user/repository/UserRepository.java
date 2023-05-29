package com.team4.prompt.user.repository;

import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
import com.team4.prompt.user.model.Role;
import com.team4.prompt.user.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByApprovedAsc();
    List<User> findByPositionAndApproved(Position position, boolean approved);
    List<User> findByPositionNotAndApproved(Position position, boolean approved);
    List<User> findByNameContaining(String name);
    List<User> findByEmployeeNumberContaining(String employeeNumber);
    List<User> findByRank(Rank rank);
    List<User> findByPosition(Position position);
    List<User> findBySkillContainingIgnoreCase(String skill);
    List<User> findByRole(Role role);
    List<User> findByRoleNot(Role role);
    List<User> findByRoleNotAndNameContaining(Role role, String name);
    List<User> findByRoleNotAndEmployeeNumberContaining(Role role, String employeeNumber);
    List<User> findByRoleNotAndPosition(Role role, Position position);
    List<User> findByRoleNotAndRank(Role role, Rank rank);
    List<User> findByRoleNotAndSkillContainingIgnoreCase(Role role, String skill);



    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    Optional<User> findByNameAndEmail(String name, String email);



    @Query("select count(*) from User where date_format(:now, '%Y') = year(enteringDate)")
    int findEnteringCountInYear(LocalDateTime now);
}
