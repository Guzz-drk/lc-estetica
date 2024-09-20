package br.com.dev.guzz.lc_estetica.useCases.categories;

import java.util.Optional;
import java.util.UUID;

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
public class GetCategoryByIdUseCase {
    
    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity execute(UUID id){
        try {
            Optional<Categories> category = this.categoriesRepository.findById(id);
            if(!category.isPresent()){
                log.info("Categoria não encontrada, id recebido: ()", id);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponseDTO("Categoria não encontrada!"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            log.error(CategoriesException.class.getSimpleName() + ": Error while finding category | id : " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
