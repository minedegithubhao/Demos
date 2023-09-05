package org.example.service.impl;

import org.example.mapper.UserDao;
import org.example.service.UserService;

/**
 * @author cxdpc
 * @date 2023-09-05 07:56
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String getNameById(String id) {
        return userDao.selectUserName("1");
    }
}
