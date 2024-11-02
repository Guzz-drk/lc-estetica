package br.com.dev.guzz.lcEstetica.controller;

import java.util.UUID;

import br.com.dev.guzz.lcEstetica.models.SimpleApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.guzz.lcEstetica.entity.Categories;
import br.com.dev.guzz.lcEstetica.useCases.categories.CreateCategoryUseCase;
import br.com.dev.guzz.lcEstetica.useCases.categories.GetCategoriesUseCase;
import br.com.dev.guzz.lcEstetica.useCases.categories.GetCategoryByIdUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoriesController {
    
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;

    @Autowired
    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @Autowired
    private GetCategoriesUseCase getCategoriesUseCase;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid Categories category){
        return this.createCategoryUseCase.execute(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(name = "id") UUID id, @RequestBody SimpleApiRequest request){
        return this.getCategoryByIdUseCase.execute(id);
    }

    @GetMapping
    public ResponseEntity<?> getCategories(@RequestBody SimpleApiRequest request){
        return this.getCategoriesUseCase.execute();
    }
}
