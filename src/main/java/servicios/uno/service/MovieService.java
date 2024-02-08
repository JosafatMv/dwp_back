package servicios.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicios.uno.model.Category;
import servicios.uno.model.Movie;
import servicios.uno.repository.CategoryRepository;
import servicios.uno.repository.MovieRepository;
import servicios.uno.utils.Message;
import servicios.uno.utils.TypesResponse;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new Message(movieRepository.findAll(), "Listado de películas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)

    public ResponseEntity<Object> findById(Long id) {
        return new ResponseEntity<>(new Message(movieRepository.findById(id), "Película encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> save(Movie movie) {

        if (movieRepository.existsByName(movie.getName())) {
            return new ResponseEntity<>(new Message("La película ya existe", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(movie.getCategory().getId());

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new Message("Categoría no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        if (!optionalCategory.get().isStatus()){
            return new ResponseEntity<>(new Message("La categoría no está activa", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        movie.setStatus(true);

        return new ResponseEntity<>(new Message(movieRepository.saveAndFlush(movie), "Categoria guardada", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> update(Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());

        if (optionalMovie.isEmpty()) {
            return new ResponseEntity<>(new Message("Película no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(movie.getCategory().getId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new Message("Categoría no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        if (!optionalCategory.get().isStatus()){
            return new ResponseEntity<>(new Message("La categoría no está activa", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        Movie movie1 = optionalMovie.get();
        movie1.setName(movie.getName());
        movie1.setCategory(movie.getCategory());
        movie1.setDuration(movie.getDuration());
        movie1.setStatus(movie.isStatus());

        movieRepository.saveAndFlush(movie1);

        return new ResponseEntity<>(new Message(movie1, "Película actualizada", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = SQLException.class)
    public ResponseEntity<Object> changeStatus(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isEmpty()) {
            return new ResponseEntity<>(new Message("Película no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Movie movie = optionalMovie.get();
        movie.setStatus(!movie.isStatus());

        movieRepository.saveAndFlush(movie);

        return new ResponseEntity<>(new Message(movie, "Estado de la película cambiado", TypesResponse.SUCCESS), HttpStatus.OK);

    }

}
