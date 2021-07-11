package com.denglitong.web;

import com.denglitong.cons.CommonConstant;
import com.denglitong.dao.Page;
import com.denglitong.domain.Board;
import com.denglitong.domain.Post;
import com.denglitong.domain.Topic;
import com.denglitong.domain.User;
import com.denglitong.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/11
 */
@Controller
@RequestMapping("/board")
public class BoardManagerController extends BaseController {

    private ForumService forumService;

    @Autowired
    public void setForumService(ForumService forumService) {
        this.forumService = forumService;
    }

    /**
     * 列出论坛下面的主题帖子
     *
     * @param boardId
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/listBoardTopics-{boardId}", method = RequestMethod.GET)
    public ModelAndView listBoardTopics(@PathVariable Integer boardId,
                                        @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view = new ModelAndView("/listBoardTopics");
        Board board = forumService.getBoardById(boardId);
        pageNo = pageNo == null ? 1 : pageNo;
        Page<Topic> pagedTopic = forumService.getPagedTopics(boardId, pageNo, CommonConstant.PAGE_SIZE);
        view.addObject("board", board);
        view.addObject("pagedTopic", pagedTopic);
        return view;
    }

    /**
     * 添加主题帖页面
     *
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/addTopicPage-{boardId}", method = RequestMethod.GET)
    public ModelAndView addTopicPage(@PathVariable Integer boardId) {
        ModelAndView view = new ModelAndView("/addTopic");
        view.addObject("boardId", boardId);
        return view;
    }

    /**
     * 添加一个主题帖
     *
     * @param request
     * @param topic
     * @return
     */
    @RequestMapping(value = "/addTopic", method = RequestMethod.GET)
    public String addTopic(HttpServletRequest request, Topic topic) {
        User user = getSessionUser(request);
        topic.setUser(user);
        Date now = new Date();
        topic.setCrateTime(now);
        topic.setLastPost(now);
        forumService.addTopic(topic);
        String targetUrl = "/board/listBoardTopics-" + topic.getBoardId();
        return "redirect:" + targetUrl;
    }

    /**
     * 列出主题的所有帖子
     *
     * @param topicId
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/listTopicPosts-{topicId}", method = RequestMethod.GET)
    public ModelAndView listTopicPosts(@PathVariable Integer topicId,
                                       @RequestParam(value = "pageNo", required = false) Integer pageNo) {
        ModelAndView view = new ModelAndView("/listTopicPosts");
        Topic topic = forumService.getTopicByTopicId(topicId);
        pageNo = pageNo == null ? 1 : pageNo;
        Page<Post> pagedPost = forumService.getPagedPost(topicId, pageNo, CommonConstant.PAGE_SIZE);
        view.addObject("topic", topic);
        view.addObject("pagedPost", pagedPost);
        return view;
    }

    /**
     * 回复主题
     *
     * @param request
     * @param post
     * @return
     */
    @RequestMapping("/addPost")
    public String addPost(HttpServletRequest request, Post post) {
        post.setCreateTime(new Date());
        post.setUser(getSessionUser(request));
        forumService.addPost(post);
        String targetUrl = "/board/listTopicPosts-" + post.getTopic().getBoardId() + ".html";
        return "redirect:" + targetUrl;
    }

    /**
     * 删除版块
     *
     * @param boardIds
     * @return
     */
    @RequestMapping(value = "/removeBoard", method = RequestMethod.GET)
    public String removeBoard(@RequestParam("boardIds") String boardIds) {
        // TODO 鉴权
        String[] boardIdArr = boardIds.split(",");
        for (String boardId : boardIdArr) {
            forumService.removeTopic(new Integer(boardId));
        }
        String targetUrl = "/index.html";
        return "redirect:" + targetUrl;
    }

    /**
     * 设置精华帖
     *
     * @param topicIds
     * @param boardId
     * @return
     */
    @RequestMapping(value = "/makeDigestTopic", method = RequestMethod.GET)
    public String makeDigestTopic(@RequestParam("topicIds") String topicIds,
                                  @RequestParam("boardId") String boardId) {
        // TODO 鉴权
        String[] topicIdArr = topicIds.split(",");
        for (String topicId : topicIdArr) {
            forumService.makeDigestTopic(new Integer(topicId));
        }
        String targetUrl = "/board/listBoardTopics-" + boardId + ".html";
        return "redirect:" + targetUrl;
    }
}
