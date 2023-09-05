package org.example.dao.impl;

import org.example.dao.AccountDao;
import org.example.pojo.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cxdpc
 * @date 2023-09-03 14:29
 */
@Repository
public class AccountDaoInMemoryImpl implements AccountDao {

    private Map<Long, Account> accountsMap = new HashMap<>();

    {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setOwnerName("John");
        account1.setBalance(10.0);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setOwnerName("Mary");
        account2.setBalance(20.0);

        accountsMap.put(account1.getId(), account1);
        accountsMap.put(account2.getId(), account2);

    }
    @Override
    public void insert(Account account) {

    }

    @Override
    public void update(Account account) {
        accountsMap.put(account.getId(), account);
    }

    @Override
    public void update(List<Account> accounts) {

    }

    @Override
    public void delete(long accountId) {

    }

    @Override
    public Account find(long accountId) {
        return accountsMap.get(accountId);
    }

    @Override
    public List<Account> find(List<Long> accountIds) {
        return null;
    }

    @Override
    public List<Account> find(String ownerName) {
        return null;
    }

    @Override
    public List<Account> find(boolean locked) {
        return null;
    }
}
