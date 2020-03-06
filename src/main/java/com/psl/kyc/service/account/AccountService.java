package com.psl.kyc.service.account;

import com.psl.kyc.model.account.Account;
import com.psl.kyc.model.account.AccountCreateRequest;
import com.psl.kyc.model.account.AccountDto;
import com.psl.kyc.model.account.AccountNumberGenerator;
import com.psl.kyc.repository.AccountNumberGeneratorRepository;
import com.psl.kyc.repository.AccountRepository;
import com.psl.kyc.service.customer.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private AccountNumberGeneratorRepository accountNumberGeneratorRepository;
    private CustomerService customerService;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          AccountNumberGeneratorRepository accountNumberGeneratorRepository) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountNumberGeneratorRepository = accountNumberGeneratorRepository;
    }

    @Transactional
    public AccountDto createAccount(AccountCreateRequest accountCreateRequest) {
        Account account = createNewAccount();
        addCustomerToAccount(accountCreateRequest, account);
        return accountRepository.modelToDto.getDestination(account);
    }

    public List<AccountDto> findAllAccountsByCustomerId(String customerId) {
        List<AccountDto> accountDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findAllByCustomers(customerId);
        accounts.forEach(it -> accountDtos.add(accountRepository.modelToDto.getDestination(it)));
        return accountDtos;
    }

    private Account createNewAccount() {
        Account account = new Account();
        account.setAccountNumber(getAccountNumber());
        return accountRepository.save(account);
    }

    private void addCustomerToAccount(AccountCreateRequest accountCreateRequest, Account account) {
        String customerId = customerService.saveAndGetCustomerId(accountCreateRequest, account.getId());
        account.getCustomers().add(customerId);
        accountRepository.save(account);
    }

    private Integer getAccountNumber() {
        return accountNumberGeneratorRepository.save(new AccountNumberGenerator()).getId();
    }
}
