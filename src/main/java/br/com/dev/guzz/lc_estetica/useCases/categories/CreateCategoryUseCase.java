package br.com.dev.guzz.lc_estetica.useCases.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.dto.ErroResponseDTO;
import br.com.dev.guzz.lc_estetica.entity.Categories;
import br.com.dev.guzz.lc_estetica.exceptions.CategoriesException;
import br.com.dev.guzz.lc_estetica.repository.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateCategoryUseCase {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity execute(Categories category){
        try {
            if((category.getDescription() == null) || (category.getDescription().isEmpty())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Description cannot be null or empty"));
            }
            if(category.getActive() == null)
                category.setActive(true);
            category.setDescription(category.getDescription().toUpperCase());
            this.categoriesRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error(CategoriesException.class.getSimpleName() + ": Error while creating category", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
