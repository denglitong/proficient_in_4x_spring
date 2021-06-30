package com.denglitong.controller;

import com.denglitong.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.testng.annotations.Test;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/28
 */
public class RestUserControllerTest {

    @Test
    public void testAsyncApi() throws InterruptedException {
        AsyncRestTemplate template = new AsyncRestTemplate();

        // 异步调用
        ListenableFuture<ResponseEntity<User>> future = template.getForEntity(
                "http://localhost:8000/mvc/rest/user/api", User.class);

        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("=== client failure ===");
            }

            @Override
            public void onSuccess(ResponseEntity<User> result) {
                System.out.println("=== client get result: " + result.getBody());
            }
        });

        System.out.println("=== not wait ===");
        Thread.sleep(10 * 1000L);
    }
}
