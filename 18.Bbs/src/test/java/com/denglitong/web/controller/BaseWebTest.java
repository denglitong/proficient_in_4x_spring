package com.denglitong.web.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.testng.annotations.BeforeClass;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/11
 */
@SpringApplicationContext({
        "classpath:/applicationContext.xml",
        "file:/Users/leon/projects/programmings/java/proficient_in_4x_spring/18.Bbs/src/main/webapp/WEN-INF/bbs-servlet.xml"
})
public class BaseWebTest extends UnitilsTestNG {

    @SpringBeanByType
    public RequestMappingHandlerAdapter handlerAdapter;

    // 声明 Request/Response 模拟对象
    public MockHttpServletRequest request;
    public MockHttpServletResponse response;
    public MockHttpSession session;

    // 执行测试前先初始模拟对象
    @BeforeClass
    public void before() {
        System.out.println("初始化 request/response");
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        request.setCharacterEncoding("UTF-8");
    }
}
