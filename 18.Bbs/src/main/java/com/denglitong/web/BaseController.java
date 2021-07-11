package com.denglitong.web;

import com.denglitong.cons.CommonConstant;
import com.denglitong.domain.User;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/7
 */
public class BaseController {

    protected static final String ERROR_MSG_KEY = "errorMsg";

    // 获取保存在 Session 中的用户对象
    protected User getSessionUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    // 将用户对象保存到 Session 中
    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
    }

    // 获取基于应用程序的 URL 绝对路径
    public final String getAppBaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url 不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }
}
