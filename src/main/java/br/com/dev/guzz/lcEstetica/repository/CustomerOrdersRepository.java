package br.com.dev.guzz.lcEstetica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lcEstetica.entity.CustomerOrders;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, UUID>{
 
    @Query(nativeQuery = true, value = "SELECT nextval('estetica.order_sequence')")
    public Long getSequenceValue();

    Long countByActive(Boolean active);
}
