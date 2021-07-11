package com.denglitong.web.controller;

import com.denglitong.domain.Board;
import com.denglitong.web.ForumManageController;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/11
 */
public class ForumManagerControllerTest extends BaseWebTest {

    @SpringBeanByType
    private ForumManageController controller;

    @Test
    public void listAllBoards() throws Exception {
        request.setRequestURI("/index");
        request.setMethod("GET");

        ModelAndView view = handlerAdapter.handle(request, response, controller);
        List<Board> boards = (List<Board>)request.getAttribute("boards");

        assertNotNull(view);
        assertEquals(view.getViewName(), "/listAllBoards");
        assertNotNull(boards);
        assertEquals(boards.size(), 1);
    }
}
