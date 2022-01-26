package com.developersstreet.tutorsage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class extends AuditModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = EAGER)
    private User creator;

    @ManyToMany(fetch = EAGER)
    private Set<Quiz> quizzes;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<User> members;
}
