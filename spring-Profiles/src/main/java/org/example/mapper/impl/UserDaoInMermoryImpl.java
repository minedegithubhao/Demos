package org.example.mapper.impl;

import org.example.mapper.UserDao;

/**
 * @author cxdpc
 * @date 2023-09-05 07:57
 */
public class UserDaoInMermoryImpl implements UserDao {
    @Override
    public String selectUserName(String id) {
        return "inMermory";
    }
}
