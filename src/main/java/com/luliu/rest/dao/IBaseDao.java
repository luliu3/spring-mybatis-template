package com.luliu.rest.dao;

import com.luliu.rest.utils.Page;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author luliu3 on 2016/8/4.
 */
public interface IBaseDao<T> {

    public T selectOne(Object obj) throws DataAccessException;

    public T selectOne(Object obj, String mapId) throws DataAccessException;

    public List<T> selectList() throws DataAccessException;

    public List<T> selectList(Object obj) throws DataAccessException;

    public List<T> selectList(Object obj, String mapId) throws DataAccessException;

    public int selectCount(Object obj) throws DataAccessException;

    public int selectCount(Object obj, String mapId) throws DataAccessException;

    public int insert(Object obj) throws DataAccessException;

    public int insert(Object obj, String mapId) throws DataAccessException;

    public int update(Object obj) throws DataAccessException;

    public int update(Object obj, String mapId) throws DataAccessException;

    public int delete(Object obj) throws DataAccessException;

    public int delete(Object obj, String mapId) throws DataAccessException;

    public Page<T> selectPage(Object obj) throws DataAccessException;

    public Page<T> selectPage(Object obj, int pageIndex, int pageSize) throws DataAccessException;

    public Page<T> selectPage(Object obj, String mapId, int pageIndex, int pageSize) throws DataAccessException;

}
