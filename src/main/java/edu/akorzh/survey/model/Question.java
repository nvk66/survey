package edu.akorzh.survey.model;

import edu.akorzh.survey.common.AnswerType;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "question")
public class Question extends AbstractEntity<Long> {

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private AnswerType type;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

}
