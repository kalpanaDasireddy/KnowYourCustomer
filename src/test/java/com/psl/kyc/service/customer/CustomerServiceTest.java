package com.psl.kyc.service.customer;

import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.customer.Customer;
import com.psl.kyc.repository.CustomerRepository;
import com.psl.kyc.service.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testsaveAndGetCustomerId_existingCustomer(){
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest("Kalpana", "Dasireddy", LocalDate.parse("1982-04-08"));
        Customer mockCustomer = getMockCustomer();
        when(customerRepository.findByForenameAndSurnameAndDateOfBirth(any(), any(), any()))
                .thenReturn(mockCustomer);

        String customerId = customerService.saveAndGetCustomerId(accountCreateRequest, "xxxxx");
        assertEquals("dddddddd", customerId);
    }

    @Test
    public void testsaveAndGetCustomerId_newCustomer(){
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest("Kalpana", "Dasireddy", LocalDate.parse("1982-04-08"));
        when(customerRepository.findByForenameAndSurnameAndDateOfBirth(any(), any(), any()))
                .thenReturn( null);
        customerService.saveAndGetCustomerId(accountCreateRequest, "xxxxx");
        assertDoesNotThrow(() -> {});
    }

    private Customer getMockCustomer(){
        List<String> accounts = new ArrayList<>();
        accounts.add("ghjfhj");
        return new Customer("dddddddd", "Kalpana", "Dasireddy", LocalDate.parse("1982-04-08"), accounts);

    }

}
