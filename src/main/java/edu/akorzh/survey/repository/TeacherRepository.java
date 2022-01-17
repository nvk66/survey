package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Teacher;
import edu.akorzh.survey.model.University;
import edu.akorzh.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    Teacher findByUser(User user);

    @Query(value = "SELECT t FROM Teacher t " +
            "LEFT OUTER JOIN FETCH t.user u " +
            "LEFT OUTER JOIN FETCH u.university uni WHERE uni = :university")
    List<Teacher> findAllByUniversity(@Param("university") University university);
}
