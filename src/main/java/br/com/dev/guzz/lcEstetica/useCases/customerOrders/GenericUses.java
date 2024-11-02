package br.com.dev.guzz.lcEstetica.useCases.customerOrders;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.guzz.lcEstetica.entity.Categories;
import br.com.dev.guzz.lcEstetica.entity.Clients;
import br.com.dev.guzz.lcEstetica.entity.CustomerOrders;
import br.com.dev.guzz.lcEstetica.entity.Services;
import br.com.dev.guzz.lcEstetica.entity.ServicesOrders;
import br.com.dev.guzz.lcEstetica.exceptions.CustomerOrdersException;
import br.com.dev.guzz.lcEstetica.models.ServiceCategory;
import br.com.dev.guzz.lcEstetica.repository.ClientsRepository;
import br.com.dev.guzz.lcEstetica.repository.CustomerOrdersRepository;
import br.com.dev.guzz.lcEstetica.repository.ServiceCategoryRepository;
import br.com.dev.guzz.lcEstetica.repository.ServicesOrdersRepository;

@Service
public class GenericUses {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    private ServicesOrdersRepository servicesOrdersRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;


    public CustomerOrders findAndValidateCustomerOrder(UUID id) {
        return customerOrdersRepository.findById(id).orElseThrow(() -> {
            throw new CustomerOrdersException("Ordem não encontrada!");
        });
    }

    public List<ServicesOrders> findServicesOrders(UUID orderId) {
        return servicesOrdersRepository.findByOrderId(orderId);
    }

    public Clients findClient(UUID clientId) {
        return clientsRepository.findById(clientId).orElse(null);
    }

    public List<Services> mapToServices(List<ServicesOrders> servicesOrders) {
        List<UUID> uuids = servicesOrders
                .stream()
                .map(ServicesOrders::getServiceId)
                .collect(Collectors.toList());
        List<Services> services = new ArrayList<>();

        uuids.stream().forEach(id -> {
            ServiceCategory serviceCategoryByServiceId = serviceCategoryRepository.getServiceCategoryByServiceId(id);
            Services service = convertToService(serviceCategoryByServiceId);
            services.add(service);
        });
    
        return services;
    }

    public Services convertToService(ServiceCategory sc){
        if(sc != null){
            Categories category = Categories.builder()
            .id(sc.getCategoryId()).description(sc.getCategoryDescription())
            .details(sc.getCategoryDetails()).active(sc.getCategoryActive())
            .createdAt(sc.getCategoryCreated()).updatedAt(sc.getCategoryUpdated())
            .build();

            Services service = Services.builder()
            .id(sc.getServiceId()).description(sc.getServiceDescription())
            .details(sc.getServiceDetails()).price(sc.getServicePrice())
            .active(sc.getServiceActive()).categoryId(category.getId())
            .category(category).createdAt(sc.getServiceCreated()).updatedAt(sc.getServiceUpdated())
            .build();

            return service;
        }

        return new Services();
    }
}
