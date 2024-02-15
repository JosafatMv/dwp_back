package servicios.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import servicios.uno.model.Category;
import servicios.uno.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    List<Movie> findByNameContaining(String name);

    List<Movie> findByDirectorContaining(String director);

    List<Movie> findByCategory(Category category);


    boolean existsByName(String name);
    

}
