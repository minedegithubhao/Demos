package org.example.service.impl;

import org.example.service.IUserService;

public class UserServiceImpl implements IUserService {

    @Override
    public void add() {
        System.out.println("增加一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除一个用户");
    }

    @Override
    public void update() {
        System.out.println("修改一个用户");
    }

    @Override
    public void find() {
        System.out.println("查找一个用户");
    }
}
