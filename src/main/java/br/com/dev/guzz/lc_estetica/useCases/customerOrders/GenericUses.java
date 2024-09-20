package br.com.dev.guzz.lc_estetica.useCases.customerOrders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lc_estetica.entity.Clients;
import br.com.dev.guzz.lc_estetica.entity.CustomerOrders;
import br.com.dev.guzz.lc_estetica.entity.Services;
import br.com.dev.guzz.lc_estetica.entity.ServicesOrders;
import br.com.dev.guzz.lc_estetica.exceptions.CustomerOrdersException;
import br.com.dev.guzz.lc_estetica.repository.CustomerOrdersRepository;
import br.com.dev.guzz.lc_estetica.repository.ServicesOrdersRepository;
import br.com.dev.guzz.lc_estetica.repository.ServicesRepository;
import br.com.dev.guzz.lc_estetica.useCases.clients.GetClientByIdUseCase;

@Service
public class GenericUses {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServicesOrdersRepository servicesOrdersRepository;

    @Autowired
    private GetClientByIdUseCase getClientByIdUseCase;

    public CustomerOrders findAndValidateCustomerOrder(UUID id) {
        return customerOrdersRepository.findById(id).orElseThrow(() -> {
            throw new CustomerOrdersException("Ordem não encontrada!");
        });
    }

    public List<ServicesOrders> findServicesOrders(UUID orderId) {
        return servicesOrdersRepository.findByOrderId(orderId);
    }

    public Clients findClient(UUID clientId) {
        Optional<Clients> client = (Optional<Clients>)  getClientByIdUseCase.execute(clientId).getBody();
        return client.get();
    }

    public List<Services> mapToServices(List<ServicesOrders> servicesOrders) {
        List<UUID> uuids = servicesOrders
                .stream()
                .map(ServicesOrders::getServiceId)
                .collect(Collectors.toList());
        List<Services> services = new ArrayList<>();
        for (UUID uuid : uuids) {
            Services service = servicesRepository.findById(uuid).get();
            services.add(service);
        }
        return services;
    }
}
