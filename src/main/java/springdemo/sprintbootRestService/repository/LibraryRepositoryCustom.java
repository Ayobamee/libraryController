package springdemo.sprintbootRestService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import springdemo.sprintbootRestService.controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<Library> findAllByAuthor(String authorName);

}
