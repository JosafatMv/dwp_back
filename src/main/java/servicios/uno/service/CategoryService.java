package servicios.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicios.uno.model.Category;
import servicios.uno.repository.CategoryRepository;
import servicios.uno.utils.Message;
import servicios.uno.utils.TypesResponse;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new Message(categoryRepository.findAll(), "Listado de categorias", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id) {
        return new ResponseEntity<>(new Message(categoryRepository.findById(id), "Categoria encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity<Object> save(Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            return new ResponseEntity<>(new Message("La categoria ya existe", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        category.setStatus(true);

        return new ResponseEntity<>(new Message(categoryRepository.saveAndFlush(category), "Categoria guardada", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity<Object> update(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new Message("Categoria no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Category category1 = optionalCategory.get();
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        category1.setStatus(category.isStatus());

        categoryRepository.saveAndFlush(category1);

        return new ResponseEntity<>(new Message(category1, "Categoria actualizada", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    public ResponseEntity<Object> changeStatus(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new Message("Categoria no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Category category = optionalCategory.get();
        category.setStatus(!category.isStatus());

        categoryRepository.saveAndFlush(category);

        return new ResponseEntity<>(new Message(category, "Estado de la categoria cambiado", TypesResponse.SUCCESS), HttpStatus.OK);

    }

}
