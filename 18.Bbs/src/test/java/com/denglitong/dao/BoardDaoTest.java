package com.denglitong.dao;

import com.denglitong.domain.Board;
import com.denglitong.test.dataset.util.XlsDataSetBeanFactory;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
public class BoardDaoTest extends BaseDaoTest {

    // 注入bean
    @SpringBean("boardDao")
    private BoardDao boardDao;

    // 测试添加版块
    @Test
    @ExpectedDataSet("Bbs.ExpectedBoards.xls") // 验证数据
    public void addBoard() throws Exception {
        List<Board> boards = XlsDataSetBeanFactory.createBeans(BoardDaoTest.class,
                "Bbs.SaveBoards.xls", "t_board", Board.class);
        for (Board board : boards) {
            boardDao.save(board);
        }
    }

    // 测试删除版块
    @Test
    @DataSet("Bbs.Boards.xls") // 准备测试数据
    @ExpectedDataSet("Bbs.ExpectedBoards.xls") // 准备验证数据
    public void removeBoard() {
        Board board = boardDao.get(7);
        boardDao.remove(board);
    }

    // 测试加载版块
    @Test
    @DataSet("Bbs.Boards.xls") // 准备测试数据
    public void getBoard() {
        Board board = boardDao.get(1);
        assertNotNull(board);
        assertEquals(board.getBoardName(), "育儿");
    }
}
