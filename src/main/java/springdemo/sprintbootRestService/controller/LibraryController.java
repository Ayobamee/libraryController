package springdemo.sprintbootRestService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springdemo.sprintbootRestService.repository.LibraryRepository;
import springdemo.sprintbootRestService.service.LibraryService;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

   private static final Logger logger=LoggerFactory.getLogger(LibraryController.class);

    //Post book to library
    //Define HTTPs method, create path and map your implementation to the library class
    @PostMapping("/addBook")
    public ResponseEntity addBookImplementation(@RequestBody Library library) {

        String id = libraryService.buildId(library.getIsbn(), library.getAisle());

        AddResponse ad = new AddResponse();
        if (!libraryService.checkBookAlreadyExist(id)) {
            logger.info("Book do not exist so creating one");

            //fetch id.
            library.setId(id);

            //jpa repository-save to post request to the database
            repository.save(library);

            //Add headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("unique", id);


            //Response to be displayed for successful request
            ad.setMessage("Book has been added for the Quales Training");
            ad.setBookid(id);


            //return ad(response and header) object to be created.
            return new ResponseEntity(ad, headers, HttpStatus.CREATED);

        }
        //
        else {
            logger.info("Book exists, skipping creation");
            ad.setMessage("Book already exists");
            ad.setBookid(id);

            return new ResponseEntity(ad, HttpStatus.ACCEPTED);
        }


    }

    //Get book by Id
    @GetMapping("/getBooks/{id}")
    public Library getBooksById(@PathVariable(value = "id") String id) {
        try {
            Library lib = repository.findById(id).get();
            return lib;
        }
        catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }


    //Get book by author
    @GetMapping("getBooks/author")
    public List<Library> getBooksByAuthorName(@RequestParam(value="authorname")String authorname)
    {

        //returning author name in json format with ok status
        return repository.findAllByAuthor(authorname);
    }

    //Update book details
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library)
    {
        //retrieve book
        Library existingBook = repository.findById(id).get();

        //update with this value
        existingBook.setAisle(library.getAisle());
        existingBook.setAuthor(library.getAuthor());
        existingBook.setBook_name(library.getBook_name());

        //saving new details in database
        repository.save(existingBook);

        //returning new book details in json format with ok status
        return new ResponseEntity<Library>(existingBook, HttpStatus.OK);

    }


    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library)
    {
        //find id
        Library libdelete = repository.findById(library.getId()).get();

        //delete
        repository.delete((libdelete));
        logger.info("Book is deleted");

        //return response book is deleted with 201 status
        return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);

    }

}

