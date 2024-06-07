package com.developersstreet.tutorsage.model.subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSection {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	private String name;
}