package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

    List<Group> findAllByUniversity(University university);
}
