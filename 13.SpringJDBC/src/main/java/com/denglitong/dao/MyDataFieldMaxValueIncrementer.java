package com.denglitong.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

/**
 * 可扩展 DataFieldMaxValueIncrementer 接口，自行编写可为多张表提供主键值，
 * 同时考虑了集群节点并发获取主键值的 Incrementer 实现
 *
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
public class MyDataFieldMaxValueIncrementer implements DataFieldMaxValueIncrementer {

    @Override
    public int nextIntValue() throws DataAccessException {
        return 0;
    }

    @Override
    public long nextLongValue() throws DataAccessException {
        return 0;
    }

    @Override
    public String nextStringValue() throws DataAccessException {
        return null;
    }
}
