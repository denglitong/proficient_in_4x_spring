package com.denglitong.service;

import com.denglitong.dao.*;
import com.denglitong.domain.*;
import com.denglitong.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
@Service
@Transactional
public class ForumService {

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 发表一个主题帖子，用户积分加 10，论坛版块的主题帖数目加 1
     *
     * @param topic
     */
    public void addTopic(Topic topic) {
        // 创建一个主题
        topicDao.save(topic);
        Board board = boardDao.get(topic.getBoardId());
        // 更新版块主题数目
        // board 处于 Hibernate 受管状态，无须显示更新，下同
        board.setTopicNum(board.getTopicNum() + 1);

        // 创建主题帖子
        topic.getMainPost().setTopic(topic);
        MainPost post = topic.getMainPost();
        post.setCreateTime(new Date());
        post.setUser(topic.getUser());
        post.setPostTitle(topic.getTopicTitle());
        post.setBoardId(topic.getBoardId());
        // 持久化主题帖子
        postDao.save(topic.getMainPost());

        // 更新用户积分
        User user = topic.getUser();
        user.setCredit(user.getCredit() + 10);
        userDao.update(user);
    }

    /**
     * 删除一个主题帖子，用户积分减 50，论坛版块主题帖数目减 1，删除该主题帖所有关联的回复帖子
     *
     * @param topicId
     */
    public void removeTopic(int topicId) {
        Topic topic = topicDao.get(topicId);
        topicDao.remove(topic);
        postDao.deleteTopicPosts(topicId);

        Board board = boardDao.get(topic.getBoardId());
        board.setTopicNum(board.getTopicNum() - 1);

        User user = topic.getUser();
        user.setCredit(user.getCredit() - 50);
    }

    /**
     * 添加一个回复帖子，用户积分加 5，主题帖子的回复数加 1 并更新最后回复时间
     *
     * @param post
     */
    public void addPost(Post post) {
        postDao.save(post);

        User user = post.getUser();
        user.setCredit(user.getCredit() + 5);
        userDao.update(user);

        Topic topic = post.getTopic();
        topic.setTopicReplies(topic.getTopicReplies() + 1);
        topic.setLastPost(new Date());
        topicDao.update(topic);
    }

    /**
     * 删除一个回复的帖子，发表回复帖子的用户积分减 20，主题帖的回复数减 1
     *
     * @param postId
     */
    public void removePost(int postId) {
        Post post = postDao.get(postId);
        postDao.remove(post);

        // topic 处于 hibernate 受管状态，无需显示更新
        Topic topic = topicDao.get(post.getTopic().getTopicId());
        topic.setTopicReplies(topic.getTopicReplies() - 1);

        User user = post.getUser();
        user.setCredit(user.getCredit() - 20);
    }

    /**
     * 创建一个新的论坛版块
     *
     * @param board
     */
    public void addBoard(Board board) {
        boardDao.save(board);
    }

    /**
     * 删除一个版块
     *
     * @param boardId
     */
    public void removeBoard(int boardId) {
        Board board = boardDao.get(boardId);
        boardDao.remove(board);
    }

    /**
     * 将帖子设置为精华
     *
     * @param topicId
     */
    public void makeDigestTopic(Integer topicId) {
        // topic 处于 hibernate 受管状态，无需显示更新
        Topic topic = topicDao.get(topicId);
        topic.setDigest(Topic.DIGEST_TOPIC);

        User user = topic.getUser();
        user.setCredit(user.getCredit() + 100);
    }

    /**
     * 获取所有的论坛版块
     *
     * @return
     */
    public List<Board> getAllBoards() {
        return boardDao.loadAll();
    }

    /**
     * 获取论坛版块某一页主题帖，以最后回复时间降序排列
     *
     * @param boardId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Topic> getPagedTopics(Integer boardId, Integer pageNo, int pageSize) {
        return topicDao.getPageTopics(boardId, pageNo, pageSize);
    }

    /**
     * 获取同主题每一页帖子，以最后回复时间降序排列
     *
     * @param topicId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Post> getPagedPost(Integer topicId, Integer pageNo, int pageSize) {
        return postDao.getPagePosts(topicId, pageNo, pageSize);
    }

    /**
     * 查找出所有标题包含 title 的主题帖
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Topic> queryTopicByTitle(String title, int pageNo, int pageSize) {
        return topicDao.queryTopicByTitle(title, pageNo, pageSize);
    }

    /**
     * 更具 boardId 获取 Board 对象
     *
     * @param boardId
     * @return
     */
    public Board getBoardById(int boardId) {
        return boardDao.get(boardId);
    }

    /**
     * 根据 topicId 获取 Topic 对象
     *
     * @param topicId
     * @return
     */
    public Topic getTopicByTopicId(Integer topicId) {
        return topicDao.get(topicId);
    }

    /**
     * 根据 postId 获取 Post 对象
     *
     * @param postId
     * @return
     */
    public Post getPostByPostId(int postId) {
        return postDao.get(postId);
    }

    /**
     * 将用户设置为论坛版块的管理员
     *
     * @param boardId
     * @param userName
     */
    public void addBoardManager(int boardId, String userName) throws UserExistException {
        User user = userDao.getUserByUserName(userName);
        if (user == null) {
            throw new UserExistException("用户名为[" + userName + "]的用户不存在");
        } else {
            Board board = boardDao.get(boardId);
            user.getManBoards().add(board);
            userDao.update(user);
        }
    }

    /**
     * 更新主题帖
     *
     * @param topic
     */
    public void updateTopic(Topic topic) {
        topicDao.update(topic);
    }

    /**
     * 更新回复帖子的内容
     *
     * @param post
     */
    public void updatePost(Post post) {
        postDao.update(post);
    }

}
