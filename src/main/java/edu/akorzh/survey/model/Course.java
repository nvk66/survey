package edu.akorzh.survey.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder

@Entity
@Table(name = "course")
public class Course extends AbstractEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "since")
    private LocalDate since;

    @Column(name = "till")
    private LocalDate till;

    @Column(name = "hours")
    private Long hours;

}
