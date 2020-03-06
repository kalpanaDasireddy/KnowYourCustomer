package com.psl.kyc.utils;

import com.psl.kyc.model.account.QAccount;
import com.psl.kyc.model.customer.QCustomer;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDate;


public class MatchCustomerPredicateBuilder {
    public static Predicate hasForename(String forename) {
        return QCustomer.customer.forename.equalsIgnoreCase(forename);
    }

    public static Predicate hasSurname(String surname) {
        return QCustomer.customer.surname.equalsIgnoreCase(surname);
    }

    public static Predicate hasDateofBirth(LocalDate dateOfBirth) {
        return QCustomer.customer.dateOfBirth.eq(dateOfBirth);
    }

    public static Predicate hasAccountId(String accountId) {
        return QCustomer.customer.accounts.contains(accountId);
    }

    public static Predicate hasCustomerId(String customerId) {
        return QCustomer.customer.id.eq(customerId);
    }

    public static void hasAccountNumber(Integer accountNumber, String accountId, JPAQuery jpaQuery) {
        QAccount account = QAccount.account;
        jpaQuery.innerJoin(account)
                .on(account.id.eq(accountId).and(account.accountNumber.eq(accountNumber)));
    }
}
