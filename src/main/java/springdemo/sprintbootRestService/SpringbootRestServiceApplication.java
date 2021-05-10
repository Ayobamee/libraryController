package springdemo.sprintbootRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springdemo.sprintbootRestService.controller.Library;
import springdemo.sprintbootRestService.repository.LibraryRepository;

@SpringBootApplication
public class SpringbootRestServiceApplication {

	@Autowired
	LibraryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestServiceApplication.class, args);
	}

	//Getting data from data base and mapping into the column in the libraray class

	/*
	@Override
	public void run(String[]args)
	{

		Library lib= repository.findById("fdsefr3").get();
		//Display the value of author in the database.
		System.out.println(lib.getAuthor());

	}
	*/

}
