package com.developersstreet.tutorsage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDataDTO {
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String birthDay;
	private String birthMonth;
	private String birthYear;
	private String profilePicUrl;
	private String phoneNumber;
}
