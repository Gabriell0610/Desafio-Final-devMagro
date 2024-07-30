package com.example.desafioDevMagroRef;

import com.example.desafioDevMagroRef.mainMenu.Main;
import com.example.desafioDevMagroRef.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioDevMagroRefApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DesafioDevMagroRefApplication.class, args);
	}

	@Autowired
	private Main mainMenu;

	@Override
	public void run(String... args) throws Exception {

		mainMenu.showMenu();
	}
}
