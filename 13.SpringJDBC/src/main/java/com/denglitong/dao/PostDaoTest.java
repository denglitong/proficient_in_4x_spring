package com.denglitong.dao;

import com.denglitong.domain.Post;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
public class PostDaoTest {

    public static void main(String[] args) throws IOException {
        String configPath = "classpath:application.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        PostDao postDao = (PostDao)ctx.getBean("postDao");
        postDao.setJdbcTemplate(jdbcTemplate);

        /*postDao.getNativeConn();*/

        ClassPathResource res = new ClassPathResource("temp.png");
        byte[] mockImg = FileCopyUtils.copyToByteArray(res.getFile());

        Post post = new Post();
        post.setUserId(1);
        post.setPostAttach(mockImg);
        post.setPostText("测试帖子的内容");
        postDao.addPost(post);

        // postDao.getAttaches(1).forEach(System.out::println);
        //
        // ByteArrayOutputStream os = new ByteArrayOutputStream();
        // postDao.getAttach(1, os);
        // System.out.println(os.toString());
    }
}
