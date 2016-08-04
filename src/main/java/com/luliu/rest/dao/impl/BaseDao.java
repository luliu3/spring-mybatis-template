package com.luliu.rest.dao.impl;

import com.luliu.rest.dao.IBaseDao;
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

    private static final String MYBATIS_MAPPER_NAMESPACE = "mapper.";
    private static final String MYBATIS_SEPARATOR = ".";
    private static final String MYBATIS_INSERT = "insert";
    private static final String MYBATIS_SELECT = "select";
    private static final String MYBATIS_LIST = "List";
    private static final String MYBATIS_DELETE = "delete";
    private static final String MYBATIS_UPDATE = "update";
    private static final String MYBATIS_COUNT = "Count";

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
        return sqlSession.selectOne(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName, obj);
    }

    public T selectOne(Object obj, String mapId) throws DataAccessException {
        return sqlSession.selectOne(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + mapId, obj);
    }

    public List<T> selectList() throws DataAccessException {
        return sqlSession.selectList(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + MYBATIS_LIST);
    }

    public List<T> selectList(Object obj) throws DataAccessException {
        return sqlSession.selectList(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + MYBATIS_LIST, obj);
    }

    public List<T> selectList(Object obj, String mapId) throws DataAccessException {
        return sqlSession.selectList(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + MYBATIS_LIST + mapId, obj);
    }

    public int selectCount(Object obj) throws DataAccessException {
        Object count = sqlSession.selectOne(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + MYBATIS_COUNT, obj);
        if (null == count) {
            return 0;
        }
        return (Integer) count;
    }

    public int selectCount(Object obj, String mapId) throws DataAccessException {
        Object count = sqlSession.selectOne(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_SELECT + ModelName + MYBATIS_COUNT + mapId, obj);
        if (null == count) {
            return 0;
        }
        return (Integer) count;
    }

    public int insert(Object obj) throws DataAccessException {
        return sqlSession.insert(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_INSERT + ModelName, obj);
    }

    public int insert(Object obj, String mapId) throws DataAccessException {
        return sqlSession.insert(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_INSERT + ModelName + mapId, obj);
    }

    public int update(Object obj) throws DataAccessException {
        return sqlSession.update(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_UPDATE + ModelName, obj);
    }

    public int update(Object obj, String mapId) throws DataAccessException {
        return sqlSession.update(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_UPDATE + ModelName + mapId, obj);
    }

    public int delete(Object obj) throws DataAccessException {
        return sqlSession.delete(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_DELETE + ModelName, obj);
    }

    public int delete(Object obj, String mapId) throws DataAccessException {
        return sqlSession.delete(MYBATIS_MAPPER_NAMESPACE + ModelName + MYBATIS_SEPARATOR + MYBATIS_DELETE + ModelName + mapId, obj);
    }
}
