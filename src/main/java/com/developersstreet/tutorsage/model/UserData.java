package com.developersstreet.tutorsage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData extends AuditModel {
    @Id @GeneratedValue(strategy = AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    @Pattern(regexp = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+", message = "First Name is invalid")
    private String firstName;

    @Column(name = "middle_name")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Middle Name is invalid")
    private String middleName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be blank")
    @Pattern(regexp = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+", message = "First Name is invalid")
    private String lastName;

    @NotNull(message = "Gender cannot be null")
    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(male|female|other)$", message = "Gender is invalid")
    private String gender;

    @Column(name = "birth_day")
    @NotNull(message = "Birth Day cannot be null")
    @NotBlank(message = "Birth Day cannot be blank")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])$", message = "Birth day is invalid")
    private String birthDay;

    @Column(name = "birth_month")
    @NotNull(message = "Birth Month cannot be null")
    @NotBlank(message = "Birth Month cannot be blank")
    @Pattern(regexp = "^(0[1-9]|1[012])$", message = "Birth month is invalid")
    private String birthMonth;

    @Column(name = "birth_year")
    @NotNull(message = "Birth Year cannot be null")
    @NotBlank(message = "Birth Year cannot be blank")
    @Pattern(regexp = "(19[7-9][0-9]|20[0-1][0-9]|202[0-1])$", message = "Birth year is invalid")
    private String birthYear;

    private String profilePicUrl;

    @Column(name = "phone_number")
    @NotNull(message = "Phone Number cannot be null")
    @NotBlank(message = "Phone Number cannot be blank")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone Number is invalid")
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, unique = true)
    private Long userId;
}
