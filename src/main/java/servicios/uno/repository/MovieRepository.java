package servicios.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import servicios.uno.model.Category;
import servicios.uno.model.Movie;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findByNameContaining(String name);

    List<Movie> findByDirectorContaining(String director);

    List<Movie> findByCategory(Category category);

    List<Movie> findByPublicationDateBetween(Date start, Date end);

    List<Movie> findByPublicationDateOrderByPublicationDate(Date publicationDate);


    boolean existsByName(String name);
    

}
