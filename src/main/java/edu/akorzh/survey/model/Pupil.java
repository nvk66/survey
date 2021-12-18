package edu.akorzh.survey.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Builder

@Entity
@Table(name = "pupil")
public class Pupil extends AbstractEntity<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "record_book")
    private String recordBook;

    @Column(name = "submitted")
    private Boolean submitted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
