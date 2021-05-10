package springdemo.sprintbootRestService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdemo.sprintbootRestService.controller.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
}
