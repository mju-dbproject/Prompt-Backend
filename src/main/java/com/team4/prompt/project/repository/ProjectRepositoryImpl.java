package com.team4.prompt.project.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team4.prompt.project.domain.Project;
import com.team4.prompt.project.domain.ProjectStatus;
import com.team4.prompt.project.domain.QProject;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Project> findBySearchOption(Integer projectStatus,
                                            String projectNumber,
                                            String client,
                                            String projectName,
                                            LocalDateTime startDate,
                                            LocalDateTime endDate) {
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
        return QProject.project.status.eq(ProjectStatus.of(status));
    }

    private BooleanExpression betweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        if(endDate == null) {
            endDate = LocalDateTime.now();
        }
        if(startDate == null) {
            startDate = LocalDateTime.MIN;
        }
        return QProject.project.createDate.between(startDate, endDate);
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

}
