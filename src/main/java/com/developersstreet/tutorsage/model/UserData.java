package com.developersstreet.tutorsage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String gender;
    private Number birth_day;
    private Number birth_month;
    private Number birth_year;
    private String profile_pic_url;
    private Long phone_number;
    private Long user_id;
}
