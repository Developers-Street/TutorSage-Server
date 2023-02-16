package com.developersstreet.tutorsage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;

import java.util.Set;

import static javax.persistence.FetchType.EAGER;

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
	
	private String statement;
	
	private Integer marks;
	
	private Integer negativeMarks;
}