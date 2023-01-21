package com.developersstreet.tutorsage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

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
}
