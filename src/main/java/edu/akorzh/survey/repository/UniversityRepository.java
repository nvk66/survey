package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long>, JpaSpecificationExecutor<University> {

    University findByGuidAndName(String guid, String name);

}
