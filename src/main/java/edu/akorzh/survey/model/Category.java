package edu.akorzh.survey.model;

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
@Table(name = "category")
public class Category extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "common")
    private Boolean common = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "category")
    private Set<Question> question = new HashSet<>(0);

}
