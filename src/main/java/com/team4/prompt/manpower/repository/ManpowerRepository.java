package com.team4.prompt.manpower.repository;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManpowerRepository extends JpaRepository<ManPower, Long> {

    Optional<ManPower> findById(Long id);
    @Query("select m from ManPower m join fetch User u where m.user = :user")
    List<ManPower> findByUser(User user);

    @Query("select m from ManPower m join fetch User u where m.id = :id")
    List<ManPower> findWithUserById(Long id);
}
