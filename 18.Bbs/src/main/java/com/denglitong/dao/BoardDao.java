package com.denglitong.dao;

import com.denglitong.domain.Board;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Repository
public class BoardDao extends BaseDao<Board> {

    private static final String GET_BOARD_NUM = "select count (b.boardId) from Board b";

    public long getBoardNum() {
        Object obj = getHibernateTemplate().iterate(GET_BOARD_NUM).next();
        return (long)obj;
    }
}
