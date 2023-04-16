package com.developersstreet.tutorsage.model.subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.FetchType.EAGER;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSection {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch = EAGER)
	private Set<MCQ> mcqQuestion;
}