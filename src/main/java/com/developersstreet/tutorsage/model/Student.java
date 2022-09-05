package com.developersstreet.tutorsage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AuditModel {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToMany
    private Set<Organization> organizations;
}
