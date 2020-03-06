package com.psl.kyc.service.customer;

import com.psl.kyc.model.account.AccountCustomerMatchRequest;
import com.psl.kyc.model.account.QAccount;
import com.psl.kyc.model.customer.QCustomer;
import com.psl.kyc.utils.MatchAccountPredicateBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.psl.kyc.utils.MatchCustomerPredicateBuilder.*;

@Slf4j
@Service
public class VerifyDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean matchInputDetails(AccountCustomerMatchRequest accountCustomerMatchRequest) {
        boolean validInput = false;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        BooleanBuilder predicate = new BooleanBuilder();
        JPAQuery jpaQuery = null;
        if ((isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerId()) ||
                isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerSurname()) ||
                isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerForename()) ||
                accountCustomerMatchRequest.getCustomerDateofBirth() != null)) {
            jpaQuery = queryFactory.from(QCustomer.customer);
            buildCustomerMatch(accountCustomerMatchRequest, predicate);
            if (isNotNullOrEmpty(accountCustomerMatchRequest.getAccountId()) &&
                    accountCustomerMatchRequest.getAccountNumber() != null) {
                hasAccountNumber(accountCustomerMatchRequest.getAccountNumber(), accountCustomerMatchRequest.getAccountId(), jpaQuery);
            }
            validInput = true;
        } else if (accountCustomerMatchRequest.getAccountNumber() != null || isNotNullOrEmpty(accountCustomerMatchRequest.getAccountId())) {
            jpaQuery = queryFactory.from(QAccount.account);
            buildAccountMatch(accountCustomerMatchRequest, predicate);
            validInput = true;
        }
        if (validInput) {
            jpaQuery.where(predicate);
            return jpaQuery.fetchCount() > 0;
        }
        return false;
    }

    private void buildCustomerMatch(AccountCustomerMatchRequest accountCustomerMatchRequest, BooleanBuilder predicate) {
        if (isNotNullOrEmpty(accountCustomerMatchRequest.getAccountId())) {
            predicate.and(hasAccountId(accountCustomerMatchRequest.getAccountId()));
        }
        if (isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerForename())) {
            predicate.and(hasForename(accountCustomerMatchRequest.getCustomerForename()));
        }
        if (isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerSurname())) {
            predicate.and(hasSurname(accountCustomerMatchRequest.getCustomerSurname()));
        }
        if (isNotNullOrEmpty(accountCustomerMatchRequest.getCustomerId())) {
            predicate.and(hasCustomerId(accountCustomerMatchRequest.getCustomerId()));
        }
        if (accountCustomerMatchRequest.getCustomerDateofBirth() != null) {
            predicate.and(hasDateofBirth(accountCustomerMatchRequest.getCustomerDateofBirth()));
        }
    }

    private void buildAccountMatch(AccountCustomerMatchRequest accountCustomerMatchRequest, BooleanBuilder predicate) {
        if (accountCustomerMatchRequest.getAccountNumber() != null) {
            predicate.and(MatchAccountPredicateBuilder.hasAccountNumber(accountCustomerMatchRequest.getAccountNumber()));
        }
        if (isNotNullOrEmpty(accountCustomerMatchRequest.getAccountId())) {
            predicate.and(MatchAccountPredicateBuilder.hasAccountId(accountCustomerMatchRequest.getAccountId()));
        }
    }

    private boolean isNotNullOrEmpty(String inputString) {
        return (inputString != null && !inputString.isEmpty());
    }
}
