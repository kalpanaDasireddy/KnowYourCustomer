package com.psl.kyc.service.customer;

import com.psl.kyc.model.account.AccountCustomerMatchRequest;
import com.psl.kyc.model.customer.QCustomer;
import com.psl.kyc.service.customer.VerifyDetailsService;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VerifyDetailsServiceTest {
    @InjectMocks
    private VerifyDetailsService verifyDetailsService;
    @Mock
    private EntityManager entityManager;

    @Test
    public void matchInputDetails_BlankInput_ReturnTrue(){
        AccountCustomerMatchRequest accountCustomerMatchRequest = new AccountCustomerMatchRequest();
        boolean matchFound = verifyDetailsService.matchInputDetails(accountCustomerMatchRequest);
        assertFalse(matchFound);
    }
}
