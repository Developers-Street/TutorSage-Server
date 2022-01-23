package com.developersstreet.tutorsage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
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

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<User> members = new ArrayList<>();
}
