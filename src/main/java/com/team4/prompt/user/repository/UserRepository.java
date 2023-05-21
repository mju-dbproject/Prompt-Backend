package com.team4.prompt.user.repository;

import com.team4.prompt.user.model.Position;
import com.team4.prompt.user.model.Rank;
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
    List<User> findByRankContaining(Rank rank);
    List<User> findByPositionContaining(Position position);

    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);

    @Query("select count(*) from User where date_format(:now, '%Y') = year(enteringDate)")
    int findEnteringCountInYear(LocalDateTime now);
}
