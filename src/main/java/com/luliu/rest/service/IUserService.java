package com.luliu.rest.service;

import com.luliu.rest.model.User;

import java.util.List;

/**
 * @author luliu3 on 2016/8/4.
 */
public interface IUserService {
    public List<User> getAllUser();
    public User getUser(Long id);
    public int insertUser(User user);
    public int deleteUser(Long id);
    public int updateUser(User user);
}
