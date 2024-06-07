package com.developersstreet.tutorsage.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.developersstreet.tutorsage.enums.CourseVisibilityType;
import com.developersstreet.tutorsage.model.AuditModel;
import com.developersstreet.tutorsage.model.subject.Subject;
import com.developersstreet.tutorsage.model.user.User;

import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends AuditModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = EAGER)
    private User creator;
    
    @ManyToOne(fetch = EAGER)
    private User headTutor;
    
    @Column(columnDefinition = "ENUM('ORGANIZATION', 'PUBLIC')")
    @Enumerated(STRING)
    private CourseVisibilityType visibility;
    
    @OneToMany(fetch = EAGER)
    private Set<Subject> subjects;

    @ManyToMany(fetch = EAGER)
    private Set<User> students;
    
    public void addStudents(User user) {
    	students.add(user);
    }
}
