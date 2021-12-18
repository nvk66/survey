package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.GroupSubjectTeacherLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupSubjectTeacherLinkRepository extends
        JpaRepository<GroupSubjectTeacherLink, Long>, JpaSpecificationExecutor<GroupSubjectTeacherLink> {
}
