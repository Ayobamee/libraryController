package springdemo.sprintbootRestService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springdemo.sprintbootRestService.controller.Library;
import springdemo.sprintbootRestService.controller.LibraryController;
import springdemo.sprintbootRestService.repository.LibraryRepository;
import springdemo.sprintbootRestService.service.LibraryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringbootRestServiceApplicationTests {
	@Autowired
	LibraryController con;
	@MockBean
	LibraryRepository repository;
	@MockBean
	LibraryService libraryService;

	@Test
	void contextLoads() {
	}

	//Unit test to test the add book method/logic.
	@Test
	public void addBookTest()


	{
		Library lib =buildLibrary();
		//passing data from library into mocked library service (for passing data).
		when(libraryService.buildId(lib.getIsbn(), lib.getAisle())).thenReturn(lib.getId());

		//passing id from the method to the logic for mocked library service to check if book exists
		when(libraryService.checkBookAlreadyExist(lib.getId())).thenReturn(false);


		//calling implementation of the response entitiy
		ResponseEntity response = con.addBookImplementation(buildLibrary());
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	//Creating method and instance of Data for library(because the spring bootserver is not started.
	public Library buildLibrary()
	{
		Library lib = new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("safe");
		lib.setAuthor("Ay");
		lib.setId("234");
		return lib;
	}

}
