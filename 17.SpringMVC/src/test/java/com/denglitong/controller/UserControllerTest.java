package com.denglitong.controller;

import com.denglitong.domain.User;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.Collections;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/28
 */
public class UserControllerTest {

    @Test
    public void test3() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("userName", "tom");
        form.add("password", "123456");
        form.add("age", "45");
        restTemplate.postForLocation("http://localhost:8000/mvc/user/httpMessageConverter1", form);
    }

    // 可添加函数依赖关系，声明执行先后顺序
    @Test(dependsOnMethods = "test3")
    public void test2() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("type", "png");
        byte[] response = restTemplate.postForObject(
                "http://localhost:8000/mvc/user/httpMessageConverter2/{imageId}",
                params, byte[].class, "image");
        Resource outFile = new FileSystemResource("/Users/leon/tmp/image_copy.png");
        FileCopyUtils.copy(response, outFile.getFile());
    }

    @Test(groups = "group1")
    public void group1() {
        System.out.println("group1");
    }

    @Test(dependsOnGroups = "group1", dependsOnMethods = "test3")
    public void test1() {
        System.out.println("just test dependsOnMethods");
    }

    @Test
    public void testXmlJson() {
        // MediaType mediaType = MediaType.APPLICATION_XML;
        MediaType mediaType = MediaType.APPLICATION_JSON;

        RestTemplate restTemplate = buildRestTemplate();

        User user = new User();
        user.setUserName("tom");
        user.setPassword("123456");
        user.setRealName("汤姆");

        HttpHeaders entityHeaders = new HttpHeaders();
        entityHeaders.setContentType(MediaType.valueOf(mediaType.getType() + "/"
                + mediaType.getSubtype() + ";UTF-8"));
        entityHeaders.setAccept(Collections.singletonList(mediaType));

        HttpEntity<User> requestEntity = new HttpEntity<>(user, entityHeaders);
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                "http://localhost:8000/mvc/user/xmlJson",
                HttpMethod.POST, requestEntity, User.class);

        User responseUser = responseEntity.getBody();
        Assert.assertNotNull(responseUser);
        System.out.println(responseUser);
    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
        xmlMarshaller.setStreamDriver(new StaxDriver());
        xmlMarshaller.setAnnotatedClasses(User.class);

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setMarshaller(xmlMarshaller);
        xmlConverter.setUnmarshaller(xmlMarshaller);
        restTemplate.getMessageConverters().add(xmlConverter);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonConverter);

        return restTemplate;
    }

    @Test
    public void testFormatter() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("userName", "tom");
        form.add("password", "123456");
        form.add("birthDay", "1980-01-01");
        form.add("salary", "4,500.00");

        // GET http://localhost:8000/mvc/user/formatter?userName=dlt&birthDay=1980-01-01&&salary=4,500.00
        String html = restTemplate.postForObject("http://localhost:8000/mvc/user/formatter", form, String.class);
        System.out.println(html);
        Assert.assertTrue(html.contains("tom"));
    }
}
