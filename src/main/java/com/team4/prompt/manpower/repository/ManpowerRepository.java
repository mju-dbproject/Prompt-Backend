package com.team4.prompt.manpower.repository;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.user.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManpowerRepository extends JpaRepository<ManPower, Long> {

    @Query("select m from ManPower m join fetch User u where m.user = :user")
    List<ManPower> findByUser(User user);
}
