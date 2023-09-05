package org.example.service;

import org.example.pojo.Account;

/**
 * @author cxdpc
 * @date 2023-09-03 14:25
 */
public interface AccountService {
    void transferMoney(long sourceAccountId, long targetAccountId, double amount);

    void depositMoney(long accountId, double amount);

    Account getAccount(long accountId);
}
