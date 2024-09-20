package br.com.dev.guzz.lc_estetica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dev.guzz.lc_estetica.entity.ServicesOrders;
import br.com.dev.guzz.lc_estetica.entity.ServicesOrdersId;
import java.util.List;
import java.util.UUID;


@Repository
public interface ServicesOrdersRepository extends JpaRepository<ServicesOrders, ServicesOrdersId> {
    
    List<ServicesOrders> findByOrderId(UUID orderId);
}
