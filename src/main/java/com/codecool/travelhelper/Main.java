package com.codecool.travelhelper;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.user.UserRoleTable;
import com.codecool.travelhelper.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

//	@Bean
//	PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService){
		return args -> {
			userService.saveRoleToBD(new UserRoleTable(null, "ROLE_ADMIN"));
			userService.saveRoleToBD(new UserRoleTable(null, "ROLE_USER"));
			userService.saveRoleToBD(new UserRoleTable(null, "ROLE_GUEST"));

			userService.saveUserToBD(new MyUserTable("admin@wp.pl","1"));
			userService.saveUserToBD(new MyUserTable("user@wp.pl","1"));
			userService.saveUserToBD(new MyUserTable("guest@wp.pl","1"));

			userService.setUserRole("admin@wp.pl","ROLE_ADMIN");
			userService.setUserRole("user@wp.pl","ROLE_USER");
			userService.setUserRole("guest@wp.pl","ROLE_GUEST");
		};
	}
}
