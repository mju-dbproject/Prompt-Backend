package com.team4.prompt.project.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.domain.QProject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Project> findBySearchOption(Integer projectStatus,
                                            String projectNumber,
                                            String client,
                                            String projectName,
                                            String startDate,
                                            String endDate) {
        JPAQuery<Project> query = jpaQueryFactory.selectFrom(QProject.project)
                .where(eqProjectStatus(projectStatus))
                .where(containProjectName(projectName), containProjectNumber(projectNumber), containClient(client))
                .where(betweenDate(startDate, endDate));

        return query.fetch();
    }

    private BooleanExpression eqProjectStatus(Integer status) {
        if(status == null) {
            return null;
        }
        if(status == 0){
            return null;
        }
        return QProject.project.status.eq(ProjectStatus.of(status));
    }

    private BooleanExpression betweenDate(String startDate, String endDate) {
        if(endDate == null) {
            LocalDate now = LocalDateTime.now().toLocalDate();
            endDate = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
        }
        if(startDate == null) {
            startDate = "2010-1-1";
        }

        LocalDateTime sDate = generateStartDateTime(startDate);
        LocalDateTime eDate = generateEndDateTime(endDate);
        return QProject.project.createDate.between(sDate, eDate);
    }

    private BooleanExpression containProjectName(String projectName) {
        if(projectName == null || projectName.isEmpty()) {
            return null;
        }
        return QProject.project.name.containsIgnoreCase(projectName);
    }
    private BooleanExpression containClient(String client) {
        if(client == null || client.isEmpty()) {
            return null;
        }
        return QProject.project.client.containsIgnoreCase(client);
    }

    private BooleanExpression containProjectNumber(String projectNumber) {
        if(projectNumber == null || projectNumber.isEmpty()) {
            return null;
        }
        return QProject.project.projectNumber.containsIgnoreCase(projectNumber);
    }

    private LocalDateTime generateStartDateTime(String datetime) {
        String[] s = datetime.split("-");
        int year = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int day = Integer.parseInt(s[2]);
        return LocalDateTime.of(year, month, day, 0,0);
    }
    private LocalDateTime generateEndDateTime(String datetime) {
        String[] s = datetime.split("-");
        int year = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int day = Integer.parseInt(s[2]);
        return LocalDateTime.of(year, month, day, 23,59);
    }


}
