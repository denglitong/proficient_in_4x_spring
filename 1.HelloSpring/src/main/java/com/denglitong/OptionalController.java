package com.denglitong;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/1
 */
@RestController
public class OptionalController {
    @RequestMapping(value = "/params", method = RequestMethod.GET)
    public String get(@RequestParam(required = true) String userName,
                      Optional<String> email) {
        System.out.println("userName: " + userName + ", email: " + email.get());
        return userName + ";" + email.get();
    }
}
