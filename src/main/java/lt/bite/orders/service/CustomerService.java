package lt.bite.orders.service;

import lt.bite.orders.model.dto.CustomerDTO;

import java.util.Set;

public interface CustomerService {

    Set<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
