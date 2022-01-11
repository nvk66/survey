package edu.akorzh.survey.model;

import edu.akorzh.survey.common.RatingType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "subject")
public class Subject extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "rate_type")
    @Enumerated(value = EnumType.STRING)
    private RatingType ratingType;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses = new HashSet<>(0);

}
