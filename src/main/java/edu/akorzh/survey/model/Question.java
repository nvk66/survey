package edu.akorzh.survey.model;

import edu.akorzh.survey.common.AnswerType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "question")
@ToString
public class Question extends AbstractEntity<Long> {

    @Column(name = "answer_type")
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

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>(0);

}
