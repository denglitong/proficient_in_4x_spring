package com.denglitong.web;

import com.denglitong.domain.Board;
import com.denglitong.domain.User;
import com.denglitong.service.ForumService;
import com.denglitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/8
 */
@Controller
@RequestMapping("/forum")
public class ForumManageController extends BaseController {

    private ForumService forumService;

    private UserService userService;

    @Autowired
    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 列出所有的论坛模块
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView listAllBoards() {
        ModelAndView view = new ModelAndView("/listAllBoards");
        List<Board> boards = forumService.getAllBoards();
        view.addObject("boards", boards);
        return view;
    }

    /**
     * 添加一个主题帖
     *
     * @return
     */
    @RequestMapping(value = "/addBoardPage", method = RequestMethod.GET)
    public String addBoardPage() {
        return "/addBoard";
    }

    /**
     * 添加一个主题帖
     *
     * @param board
     * @return
     */
    @RequestMapping(value = "/addBoard", method = RequestMethod.POST)
    public String addBoard(Board board) {
        forumService.addBoard(board);
        return "/addBoardSuccess";
    }

    /**
     * 指定论坛管理员的页面
     *
     * @return
     */
    @RequestMapping(value = "/setBoardManagerPage", method = RequestMethod.GET)
    public ModelAndView setBoardManagerPage() {
        ModelAndView view = new ModelAndView("/setBoardManager");
        List<Board> boards = forumService.getAllBoards();
        List<User> users = userService.getAllUsers();
        view.addObject("boards", boards);
        view.addObject("users", users);
        return view;
    }

    /**
     * 设置论坛版块管理员
     *
     * @param userName
     * @param boardId
     * @return
     */
    public ModelAndView setBoardManager(@RequestParam("userName") String userName,
                                        @RequestParam("boardId") String boardId) {
        ModelAndView view = new ModelAndView("/success");
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            view.addObject(ERROR_MSG_KEY, "用户名[" + userName + "]不存在");
            view.setViewName("/fail");
        } else {
            Board board = forumService.getBoardById(Integer.parseInt(boardId));
            user.getManBoards().add(board);
            userService.update(user);
        }
        return view;
    }

    /**
     * 用户锁定及解锁管理
     *
     * @param userName
     * @param locked
     * @return
     */
    public ModelAndView userLockManage(@RequestParam("userName") String userName,
                                       @RequestParam("locked") String locked) {
        ModelAndView view = new ModelAndView("/success");
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            view.addObject(ERROR_MSG_KEY, "用户名[" + userName + "]不存在");
            view.setViewName("/fail");
        } else {
            user.setLocked(Integer.parseInt(locked));
            userService.update(user);
        }
        return view;
    }
}
