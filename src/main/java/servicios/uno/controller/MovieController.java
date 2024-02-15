package servicios.uno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicios.uno.model.Movie;
import servicios.uno.model.MovieDto;
import servicios.uno.service.MovieService;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = {"*"})
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@RequestBody Movie movie) {
        return service.update(movie);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object> changeStatus(@PathVariable("id") Long id) {
        return service.changeStatus(id);
    }

    @PostMapping("/name/")
    public ResponseEntity<Object> findByName(@RequestBody Movie movie) {
        return service.findByName(movie);
    }

    @PostMapping("/director/")
    public ResponseEntity<Object> findByDirector(@RequestBody Movie movie) {
        return service.findByDirector(movie);
    }

    @PostMapping("/category/")
    public ResponseEntity<Object> findByCategory(@RequestBody Movie movie) {
        return service.findByCategory(movie);
    }

    @PostMapping("/publication-date-between/")
    public ResponseEntity<Object> findByPublicationDateBetween(@RequestBody MovieDto movie) {
        return service.findPublicationDateBetweenDates(movie);
    }

    @PostMapping("/publication-date/")
    public ResponseEntity<Object> findByPublicationDate(@RequestBody MovieDto movie) {
        return service.findByPublicationDateDesc(movie);
    }



}
