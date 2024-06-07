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

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQ extends AuditModel {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@ManyToOne(fetch = EAGER)
	private User author;
	
	@OneToMany(fetch = EAGER, cascade = ALL)
	private Set<MCQOption> options;
	
	private String statement;
	
	private Boolean isMultiCorrect;
}