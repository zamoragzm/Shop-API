package me.challenge.Jingle_Challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JingleChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JingleChallengeApplication.class, args);
		//SpringApplication.run(JingleChallengeApplication.class, args);


	}

}
