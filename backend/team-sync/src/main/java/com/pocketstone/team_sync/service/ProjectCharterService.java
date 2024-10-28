package com.pocketstone.team_sync.service;

import com.pocketstone.team_sync.dto.projectdto.ProjectCharterDto;
import com.pocketstone.team_sync.entity.Project;
import com.pocketstone.team_sync.entity.ProjectCharter;
import com.pocketstone.team_sync.entity.User;
import com.pocketstone.team_sync.repository.ProjectCharterRepository;
import com.pocketstone.team_sync.repository.ProjectRepository;
import com.pocketstone.team_sync.utility.ProjectValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectCharterService {

    @Autowired
    private ProjectCharterRepository projectCharterRepository;

    @Autowired
    private ProjectRepository projectRepository;


    //프로젝트 차터 생성
    public ProjectCharterDto saveProjectCharter(User user, Long projectId, ProjectCharterDto projectCharterDto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("해당 Id의 프로젝트 없음"));

        ProjectValidationUtils.validateProjectOwner(user, project);

        projectCharterRepository.save(ProjectCharter.builder()
                .project(project)
                .teamPositions(projectCharterDto.getTeamPositions())
                .vision(projectCharterDto.getVision())
                .objective(projectCharterDto.getObjective())
                .stakeholder(projectCharterDto.getStakeholder())
                .scope(projectCharterDto.getScope())
                .risk(projectCharterDto.getRisk())
                .principle(projectCharterDto.getPrinciple())
                .build());
        return projectCharterDto;

    }
    //프로젝트 아이디로 프로젝트 차터 조회
    public ProjectCharterDto findByProjectId(User user, Long projectId) {
        ProjectCharter projectCharter = projectCharterRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("해당 프로젝트 찾을 수 없음"));
        ProjectValidationUtils.validateCharterOwner(user, projectCharter);

        return new ProjectCharterDto(

            projectCharter.getTeamPositions(),
            projectCharter.getVision(),
            projectCharter.getObjective(),
            projectCharter.getStakeholder(),
            projectCharter.getScope(),
            projectCharter.getRisk(),
            projectCharter.getPrinciple()
        );
    }

    //프로젝트 차터 수정
    public ProjectCharterDto updateProjectCharterByProjectId(User user, Long projectId, ProjectCharterDto projectCharterDto) {
        ProjectCharter projectCharter = projectCharterRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("프로젝트 찾을 수 없음"));
        ProjectValidationUtils.validateCharterOwner(user, projectCharter);
        projectCharterRepository.updateProjectCharterByProjectId(projectId,
                projectCharterDto.getTeamPositions(),
                projectCharterDto.getVision(),
                projectCharterDto.getObjective(),
                projectCharterDto.getStakeholder(),
                projectCharterDto.getScope(),
                projectCharterDto.getRisk(),
                projectCharterDto.getPrinciple());
        return projectCharterDto;
    }
}
