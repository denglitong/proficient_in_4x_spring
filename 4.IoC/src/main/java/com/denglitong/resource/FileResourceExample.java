package com.denglitong.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class FileResourceExample {

    private static final String PROJECT_ROOT_PATH = "/Users/leon/projects/programmings/java" +
            "/proficient_in_4x_spring/4.IoC";

    public static void main(String[] args) throws Throwable {
        String filePath = PROJECT_ROOT_PATH + "/src/main/resources/application.properties";

        // 使用系统文件路径加载文件
        WritableResource resource1 = new PathResource(filePath);
        System.out.println(resource1);

        // 使用类路径方式加载文件
        Resource resource2 = new ClassPathResource("application.properties");
        System.out.println(resource2.getFilename());

        // 输出到资源文件是 Output
        OutputStream stream1 = resource1.getOutputStream();
        stream1.write("# 欢迎光临小春论坛".getBytes());
        stream1.close();

        // 从资源文件中读取是 Input
        InputStream inputStream1 = resource1.getInputStream();
        InputStream inputStream2 = resource2.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = 0;
        while ((i = inputStream1.read()) != -1) {
            baos.write(i);
        }
        System.out.println(baos.toString());

        EncodedResource encRes = new EncodedResource(resource1, "UTF-8");
        String content = FileCopyUtils.copyToString(encRes.getReader());
        System.out.println(content);

    }

    public void servletContextResource(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        Resource resource = new ServletContextResource(context, "/WEB-INF/jsp/login.jsp");
        System.out.println(WebUtils.getTempDir(context).getAbsolutePath() + "/"
                + resource.getFilename());
    }

}
