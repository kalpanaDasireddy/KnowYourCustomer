package com.psl.kyc.repository;

import com.googlecode.jmapper.JMapper;
import com.psl.kyc.model.account.Account;
import com.psl.kyc.model.account.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    JMapper<AccountDto, Account> modelToDto = new JMapper<>(AccountDto.class, Account.class);

    List<Account> findAllByCustomers(String customerId);
}
