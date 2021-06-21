package com.denglitong.dao;

import com.denglitong.domain.Post;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/21
 */
@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;

    private LobHandler lobHandler;

    private DataFieldMaxValueIncrementer incrementer;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }

    @Autowired
    public void setIncrementer(DataFieldMaxValueIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    public void getNativeConn() {
        try {
            // 从模板类中获取连接
            Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

            // 使用模板类的本地 JDBC 抽取器获取本地连接
            // 需要对 JdbcTemplate 注入一股本地 JDBC 抽取器
            conn = jdbcTemplate.getNativeJdbcExtractor().getNativeConnection(conn);

            // 这时可以对连接进行强制转换到某个厂商的连接了
            // OracleConnection oracleConnection = (OracleConnection)conn;
            // ...
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPost(final Post post) {
        String sql = " INSERT INTO t_post(post_id,user_id,post_text,post_attach) " +
                " VALUES(?,?,?,?) ";

        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            @Override
            protected void setValues(java.sql.PreparedStatement ps, LobCreator lobCreator)
                    throws java.sql.SQLException, DataAccessException {
                // 使用 incrementer 获取下一个主键值
                ps.setInt(1, incrementer.nextIntValue());
                ps.setInt(2, post.getUserId());
                // 设置 CLOB 字段
                lobCreator.setClobAsString(ps, 3, post.getPostText());
                // 设置 BLOB 字段
                lobCreator.setBlobAsBytes(ps, 4, post.getPostAttach());
            }
        });
    }

    // 以块的方式读取 LOB 数据（一次性读取全部数据到内存）
    public List<Post> getAttaches(final int userId) {
        String sql = "SELECT post_id, post_attach FROM t_post WHERE user_id = ? and " +
                " post_attach is not null";
        Object[] params = {userId};

        return jdbcTemplate.query(sql, params, new RowMapper<Post>() {
            @Override
            public Post mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                int postId = rs.getInt(1);
                byte[] attach = lobHandler.getBlobAsBytes(rs, 2);

                Post post = new Post();
                post.setPostId(postId);
                post.setPostAttach(attach);
                return post;
            }
        });
    }

    // 以流的方式读取
    public void getAttach(final int postId, final OutputStream os) {
        String sql = "SELECT post_attach FROM t_post WHERE post_id = ?";
        Object[] params = {postId};

        jdbcTemplate.query(sql, params, new AbstractLobStreamingResultSetExtractor() {
            @Override
            protected void handleNoRowFound() throws DataAccessException {
                System.out.println("Not Found Result!");
                super.handleNoRowFound();
            }

            @Override
            protected void streamData(java.sql.ResultSet rs)
                    throws java.sql.SQLException, DataAccessException, IOException {
                InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
                if (is != null) {
                    FileCopyUtils.copy(is, os);
                }
            }
        });
    }
}
