package com.denglitong.introduce;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class ForumService {

    public void removeTopic(int topicId) {
        System.out.println("模拟删除 Topic 记录：" + topicId);
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeForum(int forumId) {
        System.out.println("模拟删除 Forum 记录：" + forumId);
        try {
            Thread.sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
