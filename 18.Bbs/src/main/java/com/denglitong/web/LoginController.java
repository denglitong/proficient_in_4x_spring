package com.denglitong.web;

import com.denglitong.domain.User;
import com.denglitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.denglitong.cons.CommonConstant.LOGIN_TO_URL;
import static com.denglitong.cons.CommonConstant.USER_CONTEXT;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/7
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // ① 用户登录
    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {
        ModelAndView mav = new ModelAndView("forward:/login.jsp");

        User dbUser = userService.getUserByUserName(user.getUserName());
        if (dbUser == null) {
            mav.addObject(ERROR_MSG_KEY, "用户名不存在");
        } else if (!dbUser.getPassword().equalsIgnoreCase(user.getPassword())) {
            mav.addObject(ERROR_MSG_KEY, "用户密码不正确");
        } else if (dbUser.getLocked() == User.USER_LOCK) {
            mav.addObject(ERROR_MSG_KEY, "用户已被锁定，不能登录");
        } else {
            dbUser.setLastIp(request.getRemoteAddr());
            dbUser.setLastVisit(new Date());
            userService.loginSuccess(user);

            String toUrl = (String)request.getSession().getAttribute(LOGIN_TO_URL);
            request.getSession().removeAttribute(LOGIN_TO_URL);
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            mav.setViewName("redirect:" + toUrl);
        }

        return mav;
    }

    // ② 登录注销
    @RequestMapping("/doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(USER_CONTEXT);
        return "forward:/index.jsp";
    }

}
