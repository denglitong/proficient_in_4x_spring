package com.denglitong.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * 使用 @Cache 设置缓存策略
 * - NONE: 不使用缓存
 * - READ_ONLY: 只读模式，如果更新数据会抛出异常
 * - READ_WRITE: 读写模式，更新缓存会加锁，其他事务读取发现已加锁则去读库
 * - NONSTRICT_READ_WRITE: 不严格的读写模式，该模式不会对更新缓存时加锁
 * - TRANSACTIONAL: 事务模式，事务回滚时缓存也能回滚
 *
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Entity
@Table(name = "t_board")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Board extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int boardId;

    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_desc")
    private String boardDesc;

    @Column(name = "topic_num")
    private int topicNum;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }
}
