package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Pupil;
import edu.akorzh.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PupilRepository extends JpaRepository<Pupil, Long>, JpaSpecificationExecutor<Pupil> {

    Pupil findByUser(User user);

}
