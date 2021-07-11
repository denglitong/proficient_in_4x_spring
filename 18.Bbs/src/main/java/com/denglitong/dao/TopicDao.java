package com.denglitong.dao;

import com.denglitong.domain.Topic;
import org.springframework.stereotype.Repository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Repository
public class TopicDao extends BaseDao<Topic> {

    private static final String GET_BOARD_DIGEST_TOPICS =
            " from Topic where boardId = ? and digest > 0 " +
                    " order by lastPost desc, digest desc";
    private static final String GET_PAGE_TOPICS =
            " from Topic where boardId = ? order by lastPost desc";
    private static final String QUERY_TOPIC_BY_TITLE =
            " from Topic where topicTitle like ? order by lastPost desc";

    // 获取论坛版块某一页的精华主题帖子，按最后回复时间及精华级别降序排列
    public Page<Topic> getBoardDigestTopics(int boardId, int pageNo, int pageSize) {
        return pageQuery(GET_BOARD_DIGEST_TOPICS, pageNo, pageSize, boardId);
    }

    // 获取论坛版块某一页的主题帖子
    public Page<Topic> getPageTopics(int boardId, int pageNo, int pageSize) {
        return pageQuery(GET_PAGE_TOPICS, pageNo, pageSize, boardId);
    }

    // 获取和主题帖子标题模糊匹配的某一页帖子
    public Page<Topic> queryTopicByTitle(String title, int pageNo, int pageSize) {
        return pageQuery(QUERY_TOPIC_BY_TITLE, pageNo, pageSize, title);
    }
}
