package com.developersstreet.tutorsage.model.subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.developersstreet.tutorsage.model.AuditModel;
import com.developersstreet.tutorsage.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends AuditModel {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne(fetch = EAGER)
	private User tutor;
	
	@OneToMany(fetch = EAGER)
	private Set<Lecture> lectures;
	
	@OneToMany(fetch = EAGER)
	private Set<Test> tests;
	
	public void addLecture(Lecture lecture) {
		this.lectures.add(lecture);
	}
	
	public void addTest(Test test) {
		this.tests.add(test);
	}
}