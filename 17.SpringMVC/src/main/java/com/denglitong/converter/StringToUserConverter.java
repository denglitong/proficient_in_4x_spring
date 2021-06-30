package com.denglitong.converter;

import com.denglitong.domain.User;
import org.springframework.core.convert.converter.Converter;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/28
 */
public class StringToUserConverter implements Converter<String, User> {

    // <userName>:<password>:<realName>
    @Override
    public User convert(String source) {
        User user = new User();
        if (source != null) {
            String[] items = source.split(":");
            user.setUserName(items[0]);
            user.setPassword(items[1]);
            user.setRealName(items[2]);
        }
        return user;
    }
}
