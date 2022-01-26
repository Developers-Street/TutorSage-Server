package com.developersstreet.tutorsage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question extends AuditModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long Id;

    private String question;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QuestionOption correctAnswer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionOption> options;
}
