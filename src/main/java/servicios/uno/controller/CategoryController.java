package servicios.uno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicios.uno.model.Category;
import servicios.uno.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
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
    public ResponseEntity<Object> save(@RequestBody Category category) {
        return service.save(category);
    }

    @PutMapping("")
    public ResponseEntity<Object> update(@RequestBody Category category) {
        return service.update(category);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object> changeStatus(@PathVariable("id") Long id) {
        return service.changeStatus(id);
    }
}
