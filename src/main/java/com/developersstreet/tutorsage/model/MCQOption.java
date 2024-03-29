package com.developersstreet.tutorsage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQOption {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@ManyToOne(fetch = EAGER)
	private MCQ question;
	
	private String value;
	
	private boolean correct;
}
