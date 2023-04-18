package lt.bite.orders.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.CustomerDTO;
import lt.bite.orders.model.dto.converter.CustomerConverter;
import lt.bite.orders.model.entity.Customer;
import lt.bite.orders.repository.CustomerRepository;
import lt.bite.orders.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Override
    public Set<CustomerDTO> getCustomers() {
        Set<CustomerDTO> customers = new HashSet<>();
        customerRepository.findAll().forEach(customer -> customers.add(customerConverter.convertToDTO(customer)));
        return customers;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerConverter.convertToDTO(customer.get());
        } else {
            log.error("Customer with id: " + id + " not found.");
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerConverter.convertToEntity(customerDTO));
        return customerConverter.convertToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            existingCustomer.get().setName(customerDTO.getName());
            existingCustomer.get().setSurname(customerDTO.getSurname());
            existingCustomer.get().setCompanyName(customerDTO.getCompanyName());
            existingCustomer.get().setCompanyCode(customerDTO.getCompanyCode());
            existingCustomer.get().setPersonalCode(customerDTO.getPersonalCode());

            Customer updatedCustomer = customerRepository.save(existingCustomer.get());
            return customerConverter.convertToDTO(updatedCustomer);
        } else {
            log.error("Customer with id: " + id + " not found.");
            throw new NotFoundException("Customer not found.");
        }

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}