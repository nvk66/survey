package edu.akorzh.survey.repository;

import edu.akorzh.survey.model.Group;
import edu.akorzh.survey.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    @Query(value = "SELECT p FROM Permission p " +
            "LEFT OUTER JOIN FETCH p.course c " +
            "LEFT OUTER JOIN FETCH p.survey s " +
            "LEFT OUTER JOIN FETCH c.group g " +
            "LEFT OUTER JOIN FETCH c.teacher t " +
            "LEFT OUTER JOIN FETCH c.subject sub " +
            "WHERE g = :group")
    List<Permission> getPermissions(@Param(value = "group") Group group);
}
