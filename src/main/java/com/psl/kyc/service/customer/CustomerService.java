package com.psl.kyc.service.customer;

import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.customer.Customer;
import com.psl.kyc.model.customer.CustomerDto;
import com.psl.kyc.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public String saveAndGetCustomerId(AccountCreateRequest accountCreateRequest, String accountId) {
        Customer customer = Optional.ofNullable(customerRepository.findByForenameAndSurnameAndDateOfBirth(accountCreateRequest.getCustomerForename(),
                accountCreateRequest.getCustomerSurname(), accountCreateRequest.getCustomerDateofBirth()))
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setForename(accountCreateRequest.getCustomerForename());
                    newCustomer.setDateOfBirth(accountCreateRequest.getCustomerDateofBirth());
                    newCustomer.setSurname(accountCreateRequest.getCustomerSurname());
                    customerRepository.save(newCustomer);
                    return newCustomer;
                });
        return customer.getId();
    }

    public List<CustomerDto> findCustomersByAccountId(String accountId) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<Customer> customers = customerRepository.findAllByAccounts(accountId);
        customers.forEach(it -> customerDtos.add(customerRepository.modelToDto.getDestination(it)));
        return customerDtos;
    }
}
