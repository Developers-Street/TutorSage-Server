package com.developersstreet.tutorsage.model.subject;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.developersstreet.tutorsage.enums.TestStatus;
import com.developersstreet.tutorsage.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.CascadeType.ALL;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test extends AuditModel {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	private String name;
	
	@Column(columnDefinition = "ENUM('live', 'onhold', 'expired')")
	@Enumerated(STRING)
	private TestStatus status;
	
	private Date startTime;
	
	private Date endTime;
	
	private Long duration;
	
	@OneToMany(fetch = EAGER, cascade = ALL)
	private Set<TestSection> sections;
}