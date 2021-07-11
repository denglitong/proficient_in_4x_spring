package com.denglitong.dao;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Component
public abstract class BaseDao<T> implements Serializable {

    private final Class<T> entityClass;

    private HibernateTemplate hibernateTemplate;

    /**
     * 通过反射获取子类确定的泛型类
     */
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        entityClass = (Class<T>)params[0];
    }

    public T load(Serializable id) {
        return getHibernateTemplate().load(entityClass, id);
    }

    public T get(Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    /**
     * 获取 PO 的所有对象
     *
     * @return
     */
    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Transactional
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Transactional
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Transactional
    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    // 执行 HSQL 查询
    public List<T> find(String sql) {
        return (List<T>)getHibernateTemplate().find(sql);
    }

    public List<T> find(String sql, Object... params) {
        return (List<T>)getHibernateTemplate().find(sql, params);
    }

    // 对延迟加载的实体 PO 进行初始化（在有二级缓存的情况下）
    public void initialize(Object entity) {
        getHibernateTemplate().initialize(entity);
    }

    public Page<T> pageQuery(String hql, int pageNo, int pageSize, Object... params) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        //  Count 查询
        String countQueryStr = "select count (*) " + removeSelect(removeOrders(hql));
        Object obj = getHibernateTemplate().iterate(countQueryStr, params).next();
        long totalCount = (long)obj;

        if (totalCount < 1) {
            return new Page<>();
        }

        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, params);
        //
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page<>(startIndex, totalCount, pageSize, list);
    }

    public Query createQuery(String hql, Object... params) {
        Assert.hasText(hql);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query;
    }

    // 去掉 sql 的 select 字句
    public String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, "sql: " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    public String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hql);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "");
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public Session getSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
