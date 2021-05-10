package springdemo.sprintbootRestService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import springdemo.sprintbootRestService.controller.Library;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {

    @Autowired
    LibraryRepository repository;

    @Override
    public List<Library> findAllByAuthor(String authorName) {

        //create an empty array for books with authorname
        List<Library> bookswithAuthor = new ArrayList<Library>();

        //fetch all the books in database and keep it in an array of books.
        List<Library> books = repository.findAll();


        for (Library item: books)
            //write a condition to check that authors have an author name
          if(item.getAuthor().equalsIgnoreCase(authorName))
          {
              //if so add it to the empty array created.
              bookswithAuthor.add(item);

            }


        return bookswithAuthor;

    }
}