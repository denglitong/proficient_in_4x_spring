package com.denglitong.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 5637904229982113107L;

    private static final int DEFAULT_PAGE_SIZE = 20;

    private long start;

    private long totalCount;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private List<T> data;

    public Page() {
        this(0, 0, DEFAULT_PAGE_SIZE, Collections.emptyList());
    }

    /**
     * 默认构造方法
     *
     * @param start      本页数据在数据库中的起始位置，从 0 开始
     * @param totalCount 数据库中总记录调试
     * @param pageSize   本页容量
     * @param data       本页包含的数据
     */
    public Page(long start, long totalCount, int pageSize, List<T> data) {
        this.start = start;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public long getTotalPageCount() {
        return (long)Math.ceil((double)totalCount / pageSize);
    }

    public long getCurrentPageNo() {
        return start / pageSize + 1;
    }

    public boolean isHasNextPage() {
        return getCurrentPageNo() < getTotalPageCount();
    }

    public boolean isHasPreviousPage() {
        return getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集中的位置
     *
     * @param pageNo   从 1 开始的页号
     * @param pageSize 每页记录条数
     * @return 该页第一条数据在数据集中的位置
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 获取任一页第一条数据在数据集中的位置，每页条数使用默认值
     *
     * @see #getStartOfPage(int, int)
     */
    public static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
}
