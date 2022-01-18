package edu.akorzh.survey.model;

import edu.akorzh.survey.common.SurveyAim;
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
@ToString
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

    @OneToMany
    private Set<Permission> permissions = new HashSet<>(0);

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "survey_subject",
//            joinColumns = {@JoinColumn(name = "survey_id")},
//            inverseJoinColumns = {@JoinColumn(name = "course_id")})
//    private Set<Course> courses = new HashSet<>(0);

}
