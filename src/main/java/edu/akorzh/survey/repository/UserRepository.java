package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.University;
import edu.akorzh.survey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String login);

    List<User> findUsersByUniversityAndConfirmedFalse(University university);

}
