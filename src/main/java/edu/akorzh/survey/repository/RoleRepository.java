package edu.akorzh.survey.repository;

import edu.akorzh.survey.common.UserRoles;
import edu.akorzh.survey.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRoles name);
}
