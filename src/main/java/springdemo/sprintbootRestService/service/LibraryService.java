package springdemo.sprintbootRestService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdemo.sprintbootRestService.controller.Library;
import springdemo.sprintbootRestService.repository.LibraryRepository;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository repository;

    public String buildId(String isbn, int aisle) {

        return isbn+aisle;
    }


    public boolean checkBookAlreadyExist(String id) {
    Optional<Library> lib = repository.findById(id);
    if(lib.isPresent())
        return true;

    else

        return false;

    }

}
