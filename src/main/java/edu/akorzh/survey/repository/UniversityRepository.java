package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long>, JpaSpecificationExecutor<University> {

    University findByGuidAndName(String guid, String name);

    Optional<University> findByGuid(String guid);

}
