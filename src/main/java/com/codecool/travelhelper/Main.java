package com.codecool.travelhelper;

import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.aws.database.tables.MyUser;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

//	@Bean
//	ApplicationRunner applicationRunner(UserRepository userRepository) {
//		return args -> {
//			Date date = new Date(System.currentTimeMillis());
//			userRepository.save(new MyUser("Sebastian",
//					"Sebix",
//					date,
//					"seba@gmail.com",
//					"111",
//					"link to awatar",
//					"link to instagram",
//					"link to facebook",
//					"best scrum master",
//					"admin",
//					false ));
//			userRepository.save(new MyUser("Jakub",
//					"Treebee",
//					date,
//					"kubagem@gmail.com",
//					"222",
//					"link to awatar",
//					"link to instagram",
//					"link to facebook",
//					"very helpful programmer",
//					"user",
//					true ));
//		};
//	}
}
