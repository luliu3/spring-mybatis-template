package com.luliu.rest.dao.impl;

import com.luliu.rest.dao.IBaseDao;
import com.luliu.rest.utils.Page;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * 按照以下命名规则访问Mapper.xml
 * select: mapper.{ModelName}.select{ModelName}[List[{mapId}]]
 * insert: mapper.{ModelName}.insert{ModelName}[{mapId}]
 * update: mapper.{ModelName}.update{ModelName}[{mapId}]
 * delete: mapper.{ModelName}.delete{ModelName}[{mapId}]
 *
 * @author luliu3 on 2016/8/4.
 */

public class BaseDao<T> implements IBaseDao<T> {

    private static final String NAMESPACE = "mapper.";
    private static final String SEPARATOR = ".";
    private static final String INSERT = "insert";
    private static final String SELECT = "select";
    private static final String LIST = "List";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private static final String COUNT = "Count";
    private static final String PAGE = "Page";
    private static final String NULL_STRING = "";

    @Resource
    private SqlSession sqlSession;

    private String ModelName;

    public BaseDao() throws DataAccessException {
        Type t = getClass().getGenericSuperclass();
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            this.ModelName = ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
        }
    }

    public T selectOne(Object obj) throws DataAccessException {
        return this.selectOne(obj, NULL_STRING);
    }

    public T selectOne(Object obj, String mapId) throws DataAccessException {
        return sqlSession.selectOne(NAMESPACE + ModelName + SEPARATOR + SELECT + ModelName + mapId, obj);
    }

    public List<T> selectList() throws DataAccessException {
        return this.selectList(null, NULL_STRING);
    }

    public List<T> selectList(Object obj) throws DataAccessException {
        return this.selectList(obj, NULL_STRING);
    }

    public List<T> selectList(Object obj, String mapId) throws DataAccessException {
        if (null == obj) {
            return sqlSession.selectList(NAMESPACE + ModelName + SEPARATOR + SELECT + ModelName + LIST + mapId);
        }
        return sqlSession.selectList(NAMESPACE + ModelName + SEPARATOR + SELECT + ModelName + LIST + mapId, obj);
    }

    public int selectCount(Object obj) throws DataAccessException {
        return this.selectCount(obj, NULL_STRING);
    }

    public int selectCount(Object obj, String mapId) throws DataAccessException {
        Object count = sqlSession.selectOne(NAMESPACE + ModelName + SEPARATOR + SELECT + ModelName + COUNT + mapId, obj);
        if (null == count) {
            return 0;
        }
        return (Integer) count;
    }

    public int insert(Object obj) throws DataAccessException {
        return this.insert(obj, NULL_STRING);
    }

    public int insert(Object obj, String mapId) throws DataAccessException {
        return sqlSession.insert(NAMESPACE + ModelName + SEPARATOR + INSERT + ModelName + mapId, obj);
    }

    public int update(Object obj) throws DataAccessException {
        return this.update(obj, NULL_STRING);
    }

    public int update(Object obj, String mapId) throws DataAccessException {
        return sqlSession.update(NAMESPACE + ModelName + SEPARATOR + UPDATE + ModelName + mapId, obj);
    }

    public int delete(Object obj) throws DataAccessException {
        return this.delete(obj, NULL_STRING);
    }

    public int delete(Object obj, String mapId) throws DataAccessException {
        return sqlSession.delete(NAMESPACE + ModelName + SEPARATOR + DELETE + ModelName + mapId, obj);
    }

    public Page<T> selectPage(Object obj) throws DataAccessException {
        return this.selectPage(obj, Page.DEFAULT_SIZE, 1);
    }

    public Page<T> selectPage(Object obj, int pageSize, int pageIndex) throws DataAccessException {
        return this.selectPage(obj, NULL_STRING, pageSize, pageIndex);
    }

    public Page<T> selectPage(Object obj, String mapId, int pageSize, int pageIndex) throws DataAccessException {

        List<T> data_list = this.sqlSession.selectList(NAMESPACE + ModelName + SEPARATOR + SELECT + ModelName + PAGE + mapId, obj);
        int record_count = this.selectCount(obj);
        int page_size = pageSize <= 0 ? Page.DEFAULT_SIZE : pageSize;
        int page_count = Page.pageCount(record_count, page_size);
        int page_index = Page.pageIndex(page_count, pageIndex);
        List<Integer> page_list = Page.pageList(page_count, page_index);

        return new Page<T>(data_list, record_count, page_size, page_count, page_index, page_list);
    }

}
