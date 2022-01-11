package edu.akorzh.survey.model;

import edu.akorzh.survey.common.Grade;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder

@Entity
@Table(name = "teacher")
public class Teacher extends AbstractEntity<Long> {

    @Column(name = "grade")
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    @Column(name = "teaching_date")
    private LocalDate teachingDate;

    @Column(name = "submitted", columnDefinition = "boolean default false")
    private Boolean submitted = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses = new HashSet<>(0);

}
