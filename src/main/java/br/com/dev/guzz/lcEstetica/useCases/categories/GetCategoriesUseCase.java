package br.com.dev.guzz.lcEstetica.useCases.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Categories;
import br.com.dev.guzz.lcEstetica.exceptions.CategoriesException;
import br.com.dev.guzz.lcEstetica.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetCategoriesUseCase {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity<?> execute(){
        try {
            List<Categories> categories = this.categoriesRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(categories);
        } catch (Exception e) {
            log.error(CategoriesException.class.getSimpleName() + ": Error while finding all categories", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
