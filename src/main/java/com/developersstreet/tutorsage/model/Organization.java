package com.developersstreet.tutorsage.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.developersstreet.tutorsage.enums.OrganizationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization extends AuditModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "Organization name must not be blank")
    private String name;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "Organization email cannot be blank")
    @Email(message = "Email is not valid")
    private String email;
    
    private String description;
    
    private String logoUrl;
    
    @Column(columnDefinition = "ENUM('school', 'college', 'coaching')")
    @Enumerated(STRING)
    private OrganizationType type;

    @ManyToOne(fetch = EAGER)
    private User creator;

    @ManyToOne(fetch = EAGER)
    private User admin;

    @ManyToMany(fetch = EAGER)
    private Set<User> students;
    
    @OneToMany(fetch = EAGER)
    private Set<Course> courses;
    
    public void addStudent(User user) {
    	students.add(user);
    }
    
    public void addCourse(Course course) {
    	courses.add(course);
    }
    
    public boolean checkIfStudent(User user) {
    	return students.contains(user);
    }
    
    public boolean checkIfCourse(Course course) {
    	return courses.contains(course);
    }
}