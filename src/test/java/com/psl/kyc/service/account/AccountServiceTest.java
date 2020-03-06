package com.psl.kyc.service.account;

import com.psl.kyc.model.account.Account;
import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.account.AccountDto;
import com.psl.kyc.model.account.AccountNumberGenerator;
import com.psl.kyc.model.customer.Customer;
import com.psl.kyc.repository.AccountNumberGeneratorRepository;
import com.psl.kyc.repository.AccountRepository;
import com.psl.kyc.repository.CustomerRepository;
import com.psl.kyc.service.account.AccountService;
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

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountNumberGeneratorRepository accountNumberGeneratorRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testCreateAccount_ProvidedValidInput(){
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest("Kalpana", "Dasireddy", LocalDate.parse("1982-04-08"));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account("xxxxx", 1,  new ArrayList<>()));
        when(accountNumberGeneratorRepository.save(any(AccountNumberGenerator.class))).thenReturn(new AccountNumberGenerator(1));
        AccountDto accountDto = accountService.createAccount(accountCreateRequest);
        assertEquals(1, accountDto.getAccountNumber());
        assertEquals("xxxxx", accountDto.getId());
    }
}
