package com.carles2701.springapp082.Spring.Boot.App82;

import com.carles2701.springapp082.Spring.Boot.App82.model.Role;
import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import com.carles2701.springapp082.Spring.Boot.App82.repository.RoleRepository;
import com.carles2701.springapp082.Spring.Boot.App82.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner mappingDemo(UserRepository userRepository,
										 RoleRepository roleRepository) {
		return args -> {

			User user1 = new User();
			user1.setName("Jan");
			user1.setUsername("jank21");
			user1.setEmail("jank@gmail.com");
			user1.setDateOfBirth(LocalDate.of(1990,2,25));
			user1.setPassword(passwordEncoder().encode("12345"));
			user1.setSurname("Kowal");

			User user2 = new User();
			user2.setName("Piotr");
			user2.setUsername("pjoterdebesta");
			user2.setEmail("debesta@gmail.com");
			user2.setDateOfBirth(LocalDate.of(1990,2,25));
			user2.setPassword(passwordEncoder().encode("12345"));
			user2.setSurname("Besta");

			userRepository.save(user1);
			userRepository.save(user2);


			Role role1 = new Role();
			role1.setName("ROLE_USER");

			Role role2 = new Role();
			role2.setName("ROLE_ADMIN");

			Role role3 = new Role();
			role3.setName("ROLE_SUPER_ADMIN");

			roleRepository.saveAll(Arrays.asList(role1, role2, role3));

			user1.getRoles().addAll(Arrays.asList(role1, role2, role3));
			user2.getRoles().addAll(Arrays.asList(role2, role3));

			userRepository.save(user1);
			userRepository.save(user2);
		};
	}

}
