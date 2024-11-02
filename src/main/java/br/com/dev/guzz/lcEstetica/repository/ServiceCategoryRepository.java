package br.com.dev.guzz.lcEstetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lcEstetica.models.ServiceCategory;

import java.util.UUID;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, UUID>{
    
    @Query(nativeQuery = true, value = "select s.id as service_id, s.description as service_description, " + 
        "s.details as service_details, s.price as service_price, " + 
        "s.active as service_active, s.created_at as service_created, s.updated_at as service_updated, " +
        "c.id as category_id, c.description as category_description, c.details as category_details, " + 
        "c.active as category_active, c.created_at as category_created, c.updated_at as category_updated " + 
        "from estetica.services s " +
        "left join estetica.categories c " + 
        "on s.category_id = c.id " + 
        "where s.id = :id")
    public ServiceCategory getServiceCategoryByServiceId(UUID id);
}
