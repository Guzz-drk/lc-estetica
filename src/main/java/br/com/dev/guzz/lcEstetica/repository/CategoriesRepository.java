package br.com.dev.guzz.lcEstetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lcEstetica.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, UUID>{
    
}
