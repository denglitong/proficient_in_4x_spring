package com.denglitong.dao;

import com.denglitong.domain.Forum;
import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/20
 */
@Repository
public class ForumDao {

    private static final String ADD_FORUM_SQL =
            "INSERT INTO t_forum(forum_name,forum_desc) VALUES (?,?)";
    private static final String QUERY_FORUM_BY_ID_SQL =
            "SELECT forum_name, forum_desc FROM t_forum WHERE id = ?";
    private static final String QUERY_FORUM_BY_ID_RANGE_SQL =
            "SELECT forum_id, forum_name, forum_desc FROM t_forum WHERE forum_id between ? and ?";
    private static final String QUERY_FORUM_NUM_SQL =
            "SELECT COUNT(1) FROM t_forum";
    private static final String QUERY_TOPIC_STATISTICS_BY_USER_ID_SQL =
            "SELECT topic_replies, topic_views FROM t_topic WHERE user_id = ?";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    // 插入单条数据
    public void addForum(Forum forum) {
        Object[] params = {forum.getForumName(), forum.getForumDesc()};
        jdbcTemplate.update(ADD_FORUM_SQL, params);
    }

    public void addForum1(Forum forum) {
        Object[] params = {forum.getForumName(), forum.getForumDesc()};
        int[] argTypes = {java.sql.Types.VARCHAR, java.sql.Types.VARCHAR};
        jdbcTemplate.update(ADD_FORUM_SQL, params, argTypes);
    }

    // 插入单条数据，使用回调接口
    // 实际使用中，优先使用不带回调接口的 JdbcTemplate 方法
    // 回调并不能带来额外的好处，还使得代码变臃肿；
    // 并且 JdbcTemplate 简洁方法内部已经帮我们封装了回调示例
    public void addForum2(final Forum forum) {
        jdbcTemplate.update(ADD_FORUM_SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
                // PreparedStatement 绑定参数下标索引从 1 开始
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
            }
        });
    }

    public void addForum3(final Forum forum) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection conn)
                    throws java.sql.SQLException {
                java.sql.PreparedStatement ps = conn.prepareStatement(ADD_FORUM_SQL);
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
                return ps;
            }
        });
    }

    // 插入单条数据，带返回自增 ID
    public void addForumWithGenerateId(final Forum forum) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public java.sql.PreparedStatement createPreparedStatement
                    (java.sql.Connection conn) throws java.sql.SQLException {
                // 在准备语句时，声明 Statement.RETURN_GENERATED_KEYS 让底层数据连接在执行 update 语句时返回 id
                java.sql.PreparedStatement ps = conn.prepareStatement(ADD_FORUM_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
                return ps;
            }
        }, keyHolder);
        forum.setForumId(keyHolder.getKey().intValue());
    }

    // 批量插入
    public void batchAddForums(List<Forum> forums) {
        jdbcTemplate.batchUpdate(ADD_FORUM_SQL, new BatchPreparedStatementSetter() {
            // 参数绑定
            @Override
            public void setValues(java.sql.PreparedStatement ps, int i)
                    throws java.sql.SQLException {
                Forum forum = forums.get(i);
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
            }

            // 指定批次大小（整个批次的大小）
            // BatchPreparedStatementSetter 是一次性批量提交数据，而不是分批提交
            // 如果 List 太大希望分多次提交，则先将 List 分割为小批次的 list，
            // 但是 getBatchSize() 是一定需要和本批次的条数相等的
            @Override
            public int getBatchSize() {
                return forums.size();
            }
        });
    }

    public void addForumByNamedParams(final Forum forum) {
        final String sql = "INSERT INTO t_forum(forum_name, forum_desc) " +
                " VALUES (:forumName, :forumDesc)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("forumName", forum.getForumName())
                .addValue("forumDesc", forum.getForumDesc());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public Forum getForumById(final int forumId) {
        final Forum forum = new Forum();
        Object[] params = {forumId};

        jdbcTemplate.query(QUERY_FORUM_BY_ID_SQL, params, new RowCallbackHandler() {
            @Override
            public void processRow(java.sql.ResultSet rs) throws java.sql.SQLException {
                forum.setForumId(forumId);
                forum.setForumName(rs.getString("form_name"));
                forum.setForumDesc(rs.getString("form_desc"));
            }
        });
        return forum;
    }

    // 多行结果集
    public List<Forum> getForumsByIdRange(final int fromId, final int toId) {
        final List<Forum> forums = new ArrayList<>();
        Object[] params = {fromId, toId};

        jdbcTemplate.query(QUERY_FORUM_BY_ID_RANGE_SQL, params, new RowCallbackHandler() {
            @Override
            public void processRow(java.sql.ResultSet rs) throws java.sql.SQLException {
                Forum forum = new Forum();
                forum.setForumId(rs.getInt("forum_id"));
                forum.setForumName(rs.getString("forum_name"));
                forum.setForumDesc(rs.getString("forum_desc"));
                forums.add(forum);
            }
        });
        return forums;
    }

    public List<Forum> getForumsByIdRange2(final int fromId, final int toId) {
        Object[] params = {fromId, toId};

        //  RowMapper 更适合用在多行结果集
        // 但是，在处理大结果集时，如果使用 RowMapper，那么采用的方式是将结果集的所有数据都放到一个
        // List<T>对象中，这将会占用大量的 JVM 内存深圳引发 OOM 异常，
        // 这时可使用 RowCallbackHandler 接口
        // （可一边获取数据一边完成处理而不必返回 List，数据不会在内存中堆积）
        return jdbcTemplate.query(QUERY_FORUM_BY_ID_RANGE_SQL, params, new RowMapper<Forum>() {
            @Override
            public Forum mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                Forum forum = new Forum();
                forum.setForumId(rs.getInt("forum_id"));
                forum.setForumName(rs.getString("forum_name"));
                forum.setForumDesc(rs.getString("forum_desc"));
                return forum;
            }
        });
    }

    public List<Forum> getForumByIdRange3(List<Integer> ids) {
        final String sql = "SELECT forum_id, forum_name, forum_desc " +
                " FROM t_forum WHERE forum_id IN (:ids) ";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("ids", ids); // IN 可接受 List 参数，在底层会根据 LIST SIZE 转换为 ?,?,..,?

        return namedParameterJdbcTemplate.query(sql, params, new RowMapper<Forum>() {
            @Override
            public Forum mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                Forum forum = new Forum();
                forum.setForumId(rs.getInt("forum_id"));
                forum.setForumName(rs.getString("forum_name"));
                forum.setForumDesc(rs.getString("forum_desc"));
                return forum;
            }
        });
    }

    // 单值查询
    public int getForumNum() {
        // 单值查询要求返回结果集必须有一行一列且能被转型为 RequiredType，不然将抛出异常
        return jdbcTemplate.queryForObject(QUERY_FORUM_NUM_SQL, int.class);
    }

    // 使用 RowMapper 获取单值对象
    public double getReplyRate(final int userId) {
        Object[] params = {userId};

        return jdbcTemplate.queryForObject(QUERY_TOPIC_STATISTICS_BY_USER_ID_SQL, params, new RowMapper<Double>() {
            @Override
            public Double mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
                int replies = rs.getInt("topic_replies");
                int views = rs.getInt("topic_views");
                return views > 0 ? Double.valueOf((double)replies / views)
                        : Double.valueOf(0.0);
            }
        });
    }

    public int getUserTopicNum(final int userId) {
        String sql = "{CALL P_GET_TOPIC_NUM(?,?)}";

        return jdbcTemplate.execute(sql, new CallableStatementCallback<Integer>() {
            @Override
            public Integer doInCallableStatement(java.sql.CallableStatement cs)
                    throws java.sql.SQLException, DataAccessException {
                cs.setInt(1, userId); // 绑定入参
                cs.registerOutParameter(2, java.sql.Types.INTEGER); // 注册输出参数
                cs.execute();
                return cs.getInt(2); // 获取输出参数的值
            }
        });
    }

    public int getUserTopicNum2(final int userId) {
        String sql = "{CALL P_GET_TOPIC_NUM(?,?)}";

        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory(sql);
        factory.addParameter(new SqlParameter("userId", java.sql.Types.INTEGER));
        factory.addParameter(new SqlOutParameter("topicNum", java.sql.Types.INTEGER));

        Map<String, Integer> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        CallableStatementCreator creator = factory.newCallableStatementCreator(paramsMap);

        return jdbcTemplate.execute(creator, new CallableStatementCallback<Integer>() {
            @Override
            public Integer doInCallableStatement(java.sql.CallableStatement cs)
                    throws java.sql.SQLException, DataAccessException {
                cs.execute();
                // 如果有结果集返回可使用 cs.getResultSet() 获取结果集
                return cs.getInt(2);
            }
        });
    }
}
