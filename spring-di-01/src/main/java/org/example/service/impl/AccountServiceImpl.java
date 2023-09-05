package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.pojo.Account;
import org.example.service.AccountService;

/**
 * @author cxdpc
 * @date 2023-09-03 14:26
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 转账
     * @param sourceAccountId 付款方
     * @param targetAccountId 收款方
     * @param amount 金额
     */
    @Override
    public void transferMoney(long sourceAccountId, long targetAccountId, double amount) {
        Account sourceAccount = accountDao.find(sourceAccountId);
        Account targetAccount = accountDao.find(targetAccountId);
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        accountDao.update(sourceAccount);
        accountDao.update(targetAccount);
    }

    /**
     * 存款
     * @param accountId 账户
     * @param amount 金额
     */
    @Override
    public void depositMoney(long accountId, double amount) {
        Account account = accountDao.find(accountId);
        account.setBalance(account.getBalance() + amount);
        accountDao.update(account);
    }

    /**
     * 根据id查找账户
     * @param accountId 账户id
     * @return 账户
     */
    @Override
    public Account getAccount(long accountId) {
        return accountDao.find(accountId);
    }
}
