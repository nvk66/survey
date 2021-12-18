package edu.akorzh.survey.model;

import edu.akorzh.survey.common.SurveyType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "survey")
public class Survey extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private SurveyType type;

    @Column(name = "common")
    private Boolean common = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "survey")
    private Set<Category> categories = new HashSet<>(0);

    @ManyToMany
    @JoinTable(name = "survey_subject",
            joinColumns = {@JoinColumn(name = "survey_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_subject_teacher_id")})
    private Set<GroupSubjectTeacherLink> groupSubjectTeacherLinks = new HashSet<>(0);

}
