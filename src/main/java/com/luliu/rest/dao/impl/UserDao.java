package com.luliu.rest.dao.impl;

import com.luliu.rest.dao.IUserDao;
import com.luliu.rest.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author luliu3 on 2016/8/4.
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {
}
