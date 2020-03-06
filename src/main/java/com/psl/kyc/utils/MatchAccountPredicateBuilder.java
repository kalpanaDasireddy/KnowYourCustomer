package com.psl.kyc.utils;

import com.psl.kyc.model.account.QAccount;
import com.querydsl.core.types.Predicate;

public class MatchAccountPredicateBuilder {
    public static Predicate hasAccountId(String accountId) {
        return QAccount.account.id.eq(accountId);
    }

    public static Predicate hasCustomerId(String customerId) {
        return QAccount.account.customers.contains(customerId);
    }

    public static Predicate hasAccountNumber(Integer accountNumber) {
        return QAccount.account.accountNumber.eq(accountNumber);
    }
}
