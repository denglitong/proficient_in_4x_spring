package com.denglitong.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 使用泛型<T>提供一个有CURD简洁版的 BaseDao
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/23
 */
public class BaseDao2<T> {

    private HibernateTemplate hibernateTemplate;

    private Class entityClass;

    public BaseDao2() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        entityClass = (Class)params[0];
    }

    public T get(Serializable id) {
        return (T)hibernateTemplate.get(entityClass, id);
    }

    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
}
