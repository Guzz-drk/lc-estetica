package br.com.dev.guzz.lc_estetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lc_estetica.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, UUID>{
    
}
