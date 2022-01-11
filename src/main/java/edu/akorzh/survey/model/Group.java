package edu.akorzh.survey.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder

@Entity
@Table(name = "groups")
public class Group extends AbstractEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "guid", nullable = false)
    private String guid;

    @Column(name = "year", nullable = false)
    private String year;

    // TODO: 16.12.2021 добавить List<Pupil> у группы будет красиво

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses = new HashSet<>(0);
}
