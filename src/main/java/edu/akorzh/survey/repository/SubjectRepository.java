package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Subject;
import edu.akorzh.survey.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

    List<Subject> findAllByUniversity(University university);
}
