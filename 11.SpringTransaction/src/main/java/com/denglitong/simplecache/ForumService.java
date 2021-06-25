package com.denglitong.service;

import com.denglitong.dao.ForumDao;
import com.denglitong.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/15
 */
@Service
public class ForumService {

    private TransactionTemplate transactionTemplate;

    private ForumDao forumDao;

    public void addForum(final Forum forum) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 需要在事务环境中执行的代码
                forumDao.addForum(forum);
                // 如果需要在回调接口方法中显式访问底层数据连接，则必须通过资源获取工具类得到线程绑定的数据连接
                // 这是 Spring 事务管理的底层协议
            }
        });
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Autowired
    public void setForumDao(ForumDao forumDao) {
        this.forumDao = forumDao;
    }
}
