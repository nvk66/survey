package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Teacher;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    Teacher findByUser(User user);

    List<Teacher> findAllByUniversity(University university);
}
