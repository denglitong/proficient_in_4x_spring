package com.denglitong.web;

import com.denglitong.cons.CommonConstant;
import com.denglitong.domain.User;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/7
 */
public class ForumFilter implements Filter {

    private static final String FILTERED_REQUEST = "@@session_context_filter_request";

    // ① 无需登录即可访问的 URI 资源
    private static final String[] INHERENT_ESCAPE_URIS = {
            "/index.jsp", "/index.html",
            "/login.jsp", "/login/doLogin.html",
            "/register.jsp", "/register.html",
            "/board/listBoardTopics-", "/board/listTopicPosts-"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    // ② 执行过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // ②-1 保证该过滤器在一次请求中只被调用一次
        if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
            chain.doFilter(request, response);
            return;
        }

        // ②-2 设置过滤标识，防止一次请求多次过滤
        request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        User userContext = getSessionUser(httpRequest);

        // ②-3 用户未登录，且当前 URI 资源需要登录才能访问
        if (userContext == null && isURILogin(httpRequest.getRequestURI(), httpRequest)) {
            String toUrl = httpRequest.getRequestURL().toString();
            if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
                toUrl += "?" + httpRequest.getQueryString();
            }
            // ②-4 将用户的请求 URL 保存在 Session 中，用于登录成功之后，跳到目标 URL
            httpRequest.getSession().setAttribute(CommonConstant.LOGIN_TO_URL, toUrl);

            // ②-5 转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    protected boolean isURILogin(String requestURI, HttpServletRequest request) {
        if (request.getContextPath().equalsIgnoreCase(requestURI)
                || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true;
        }
        return requestURI != null &&
                Stream.of(INHERENT_ESCAPE_URIS).anyMatch(requestURI::contains);
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }
}
