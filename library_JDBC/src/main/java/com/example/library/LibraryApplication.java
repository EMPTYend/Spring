package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Library application.
 * This class is annotated with {@code @SpringBootApplication}, which serves as a convenience
 * annotation that adds all of the following:
 * <ul>
 *   <li>{@code @Configuration}: Marks the class as a source of bean definitions.</li>
 *   <li>{@code @EnableAutoConfiguration}: Enables Spring Boot's auto-configuration mechanism.</li>
 *   <li>{@code @ComponentScan}: Enables component scanning for the package and its sub-packages.</li>
 * </ul>
 * 
 * The {@code main} method uses {@link SpringApplication#run(Class, String...)} to launch the application.
 *
 * @author Your Name
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
