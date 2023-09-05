package org.example.dao;

import org.example.pojo.Account;

import java.util.List;

/**
 * @author cxdpc
 * @date 2023-09-03 14:28
 */
public interface AccountDao {

    void insert(Account account);
    void update(Account account);
    void update(List<Account> accounts);
    void delete(long accountId);
    Account find(long accountId);
    List<Account> find(List<Long> accountIds);
    List<Account> find(String ownerName);
    List<Account> find(boolean locked);

}
