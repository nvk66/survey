package edu.akorzh.survey.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder

@Entity
@Table(name = "answer")
public class Answer extends AbstractEntity<Long> {

    @Column(name = "value")
    private Long value;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
