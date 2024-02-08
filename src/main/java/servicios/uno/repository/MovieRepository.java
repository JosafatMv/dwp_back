package servicios.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicios.uno.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    boolean existsByName(String name);
}
