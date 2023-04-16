package com.developersstreet.tutorsage.model.subject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSection_MCQ {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@ManyToOne
	private TestSection testSection;
	
	@ManyToOne
	private MCQ mcq;
	
	private Double marks;
	
	private Double negativeMarks;
}
