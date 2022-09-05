package com.developersstreet.tutorsage;

import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class TutorsageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorsageApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
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
			ArrayList<String> roles = new ArrayList<>();
			roles.add("ROLE_TUTOR");
			roles.add("ROLE_STUDENT");
			roles.add("ROLE_ORGANIZATION_ADMIN");

			for(int i = 0; i < roles.size(); i++) {
				try {
					userService.saveRole(new Role(null, roles.get(i)));
				} catch (Exception exception) {

				}
			}
		};
	}
}
