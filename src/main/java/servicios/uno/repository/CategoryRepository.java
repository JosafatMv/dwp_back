package servicios.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicios.uno.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
