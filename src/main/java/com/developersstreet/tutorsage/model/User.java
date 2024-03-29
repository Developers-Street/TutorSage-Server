package com.developersstreet.tutorsage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditModel {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is not valid")
    private String email;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "Username must not be blank")
    @Pattern(regexp = "^[A-Za-z]\\w{3,29}$", message = "Username is not valid")
    private String username;

    @NotNull
    @NotBlank(message = "Password must not be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(fetch = EAGER, cascade = CascadeType.ALL)
    private UserData userData;

    @ManyToMany(fetch = EAGER)
    private Set<Role> roles;
}
