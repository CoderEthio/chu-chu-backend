package com.chu;

import com.chu.hoax.Hoax;
import com.chu.user.UserModel;
import com.chu.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class ChuApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChuApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/1.0/users").allowedOrigins("http://localhost:3000");
			}
		};
	}


	@Bean
	@Profile("dev")
	CommandLineRunner run(UserService userService){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				//create users
				IntStream.rangeClosed(0,15).mapToObj(i->{
					UserModel user = new UserModel();
					user.setUsername("user"+i);
					user.setDisplayname("displayname" + i);
					user.setPassword("P3aaword"+i);
					return user;
				}).forEach(userService::save);
			}
		};
	}

}
