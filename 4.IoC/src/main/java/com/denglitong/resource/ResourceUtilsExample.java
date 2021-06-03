package com.denglitong.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/3
 */
public class ResourceUtilsExample {

    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath*:com/denglitong/**/*.class");
        assert resources != null;
        for (Resource resource : resources) {
            /**
             * 用 Resource 操作文件时，如果资源文件在项目发布时会被打包到 JAR 中，
             * 那么不能使用 Resource#getFile() 方法，否则会抛出 FileNotFoundException,
             * 但可以使用 Resource#getInputStream() 方法读取。
             *
             * 开发过程中容易忽略次问题，直到部署的时候才暴露，因此建议尽量以流的方式读取。
             */
            // System.out.println(resource.getFile());
            System.out.println(resource.getDescription());
        }
    }

}
