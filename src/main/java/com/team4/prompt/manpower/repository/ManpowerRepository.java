package com.team4.prompt.manpower.repository;

import com.team4.prompt.manpower.domain.ManPower;
import com.team4.prompt.manpower.domain.Task;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

public interface ManpowerRepository extends JpaRepository<ManPower, Long> {

    List<ManPower> findByProjectId(Long projectId);
    List<ManPower> findByUserAndProject_Status(User user, ProjectStatus status);
    @Query("SELECT m FROM ManPower m WHERE m.user = :user AND m.task IN :tasks AND  m.endDate = null ")
    List<ManPower> findByUserAndTaskIn(User user, List<Task> tasks);
    Optional<ManPower> findById(Long id);
    @Query("select m from ManPower m join fetch User u where m.user = :user")
    List<ManPower> findByUser(User user);

    @Query("select count(*) from ManPower m where m.user = :user and m.project = :project")
    int countByUserAndProject(User user, Project project);

    Optional<ManPower> findByUserAndProject(User user, Project project);

    @Query("select m from ManPower m join fetch User u where m.id = :id")
    List<ManPower> findWithUserById(Long id);
  
    List<ManPower> findPeersByProject(Project project);
    @Query("select m from ManPower m join fetch User u where m.project = :project")
    List<ManPower> findManPowerWithEmployeeByProject(Project project);

    ManPower findByProjectIdAndUser(Long projectId, User user);

    @Query("select m from ManPower m join fetch User u where m.user = :user")
    List<ManPower> findManPowerWithProjectByUser(User user);
}
