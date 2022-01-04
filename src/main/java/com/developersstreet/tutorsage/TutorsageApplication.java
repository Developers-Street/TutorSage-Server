package com.developersstreet.tutorsage;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class TutorsageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorsageApplication.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "john@gmail.com", "john", "John@1234", new ArrayList<>()));
			userService.saveUser(new User(null, "randy@gmail.com", "randy", "Randy@1234", new ArrayList<>()));
			userService.saveUser(new User(null, "bigshow@gmail.com", "show", "Show@1234", new ArrayList<>()));
			userService.saveUser(new User(null, "romanreigns@gmail.com", "roman", "Roman@1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("randy", "ROLE_MANAGER");
			userService.addRoleToUser("show", "ROLE_ADMIN");
			userService.addRoleToUser("roman", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("roman", "ROLE_ADMIN");
			userService.addRoleToUser("roman", "ROLE_USER");
		};
	}
}
