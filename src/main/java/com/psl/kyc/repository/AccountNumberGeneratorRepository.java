package com.psl.kyc.repository;

import com.psl.kyc.model.account.AccountNumberGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountNumberGeneratorRepository extends JpaRepository<AccountNumberGenerator,Long> {

}
