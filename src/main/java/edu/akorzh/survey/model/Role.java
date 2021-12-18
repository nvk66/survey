package edu.akorzh.survey.model;

import edu.akorzh.survey.common.UserRoles;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity<Long> {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", unique = true, nullable = false)
    private UserRoles name;
}
