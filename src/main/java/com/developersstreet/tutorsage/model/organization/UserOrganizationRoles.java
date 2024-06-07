package com.developersstreet.tutorsage.model.organization;

import javax.persistence.*;

import com.developersstreet.tutorsage.model.user.Role;
import com.developersstreet.tutorsage.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrganizationRoles {
	@Id @GeneratedValue(strategy = AUTO)
	private Long id;
	
	@OneToOne(fetch = EAGER)
	private User user;
	
	@OneToOne(fetch = EAGER)
	private Organization organization;
	
	@OneToOne(fetch = EAGER)
	private Role role;
	
	public UserOrganizationRoles(Organization o, Role r, User u) {
		user = u;
		role = r;
		organization = o;
	}
}
