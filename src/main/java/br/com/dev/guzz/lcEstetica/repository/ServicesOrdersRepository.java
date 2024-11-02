package br.com.dev.guzz.lcEstetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lcEstetica.entity.ServicesOrders;
import br.com.dev.guzz.lcEstetica.entity.ServicesOrdersId;

import java.util.List;
import java.util.UUID;


@Repository
public interface ServicesOrdersRepository extends JpaRepository<ServicesOrders, ServicesOrdersId> {
    
    List<ServicesOrders> findByOrderId(UUID orderId);
}
