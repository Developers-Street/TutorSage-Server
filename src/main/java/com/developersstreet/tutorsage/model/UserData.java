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

    @Column(nullable = false)
    private String first_name;

    private String middle_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birth_day;

    @Column(nullable = false)
    private String birth_month;

    @Column(nullable = false)
    private String birth_year;

    private String profile_pic_url;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false, unique = true)
    private Long user_id;
}
