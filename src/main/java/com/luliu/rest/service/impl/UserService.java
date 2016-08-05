package com.luliu.rest.service.impl;

import com.luliu.rest.dao.IUserDao;
import com.luliu.rest.model.User;
import com.luliu.rest.service.IUserService;
import com.luliu.rest.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luliu3 on 2016/8/4.
 */

@Service
public class UserService implements IUserService {

    @Resource
    private IUserDao userDao;

    public List<User> getAllUser() {
        return userDao.selectList();
    }

    public User getUser(Long id) {
        return userDao.selectOne(id);
    }

    public int insertUser(User user) {
        return userDao.insert(user);
    }

    public int deleteUser(Long id) {
        return userDao.delete(id);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }

    public Page<User> getUserPageInRange(int low, int high) {
        Map<String, Integer> range = new HashMap<String, Integer>();
        range.put("low", low);
        range.put("high",high);
        return userDao.selectPage(range);
    }
}
