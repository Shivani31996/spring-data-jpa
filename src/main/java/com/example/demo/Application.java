package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			Student maria = new Student(
					"Maria",
					"Jones",
					"maria.jones@gmail.com",
					21);
			Student maria2 = new Student(
					"Maria",
					"Jones",
					"maria2.jones@gmail.com",
					25);
			Student ahmed = new Student(
					"Ahmed",
					"Ali",
					"ahmed.ali@gmail.com",
					21);
			System.out.println("Adding Maria and Ahmed");

			studentRepository.saveAll(List.of(maria,ahmed,maria2));

			studentRepository.findStudentsByEmail("ahmed.ali@gmail.com")
					.ifPresentOrElse(System.out::println,
							() -> {
								System.out.println("Student with email not found");
							});

			studentRepository.
					findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual("Maria",21)
					.forEach(System.out::println);
		};
	}

}
