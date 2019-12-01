package com.zwhzzz.Service;

import com.zwhzzz.Mapper.AccountDao;
import com.zwhzzz.Pojo.Account;
import com.zwhzzz.Pojo.AccountExample;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Service
public class AccountService {

    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account getAccountByName(String name) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria()
                .andNameEqualTo(name);
        List<Account> accounts = accountDao.selectByExample(accountExample);
        if(accounts.size() > 0) {
            return accounts.get(0);
        }
        return null;
    }

    public List<Account> getAccountList(Map<String, Object> queryMap) {
        return accountDao.getAccountList(queryMap);
    }

    public int insertAccount(Account account) {
        return accountDao.insert(account);
    }


    public int updateAccount(Account account) {
        return accountDao.updateByPrimaryKeySelective(account);
    }

    public int deleteAccount(Integer id) {
        return accountDao.deleteByPrimaryKey(id);
    }

    public List<Account> getList() {
        return accountDao.selectByExample(new AccountExample());
    }

    public List<Account> getAccountById(Integer id) {

        AccountExample example = new AccountExample();
        List<Account> accounts = accountDao.selectByExample(example);
        return accounts;
    }

    public int updatePhoto(String filePath) {
        Account record = new Account();
        record.setPhoto(filePath);
        return accountDao.updateByPrimaryKeySelective(record);
    }
}




