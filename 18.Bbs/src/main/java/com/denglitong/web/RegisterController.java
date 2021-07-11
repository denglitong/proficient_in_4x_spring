package com.denglitong.web;

import com.denglitong.cons.CommonConstant;
import com.denglitong.domain.User;
import com.denglitong.exception.UserExistException;
import com.denglitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户注册控制器
 *
 * @author litong.deng@foxmail.com
 * @date 2021/7/7
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 用户注册
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView mav = new ModelAndView("/success");

        try {
            userService.register(user);
        } catch (UserExistException e) {
            mav.addObject(ERROR_MSG_KEY, "用户名已经存在，请选择其他的名字。");
            mav.setViewName("forward:/register.jsp");
        }

        setSessionUser(request, user);
        return mav;
    }
}
