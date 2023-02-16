package com.developersstreet.tutorsage.model.subject;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.developersstreet.tutorsage.enums.LectureMode;
import com.developersstreet.tutorsage.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends AuditModel {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	
	@Column(columnDefinition = "ENUM('online', 'offline')")
	@Enumerated(STRING)
	private LectureMode mode;
	
	private String onlineUrl;
	
	private Date date;
	
	private Integer duration;

}
