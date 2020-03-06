package com.psl.kyc.repository;

import com.googlecode.jmapper.JMapper;
import com.psl.kyc.model.customer.Customer;
import com.psl.kyc.model.customer.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    JMapper<CustomerDto, Customer> modelToDto = new JMapper<>(CustomerDto.class, Customer.class);

    Optional<Customer> findById(String id);

    Customer findByForenameAndSurnameAndDateOfBirth(String forename, String surname, LocalDate dateOfBirth);

    List<Customer> findAllByAccounts(String accountId);
}
