package xyz.itbs.szubrewery.services;

import xyz.itbs.szubrewery.web.model.BeerDTO;
import xyz.itbs.szubrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {
    
    CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    void updateCustomer(UUID customerId, CustomerDTO customerDTO);

    void deleteById(UUID customerId);
}
