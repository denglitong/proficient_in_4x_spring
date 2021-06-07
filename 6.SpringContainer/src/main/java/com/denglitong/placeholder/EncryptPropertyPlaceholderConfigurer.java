package com.denglitong.placeholder;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Arrays;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private final String[] encryptPropertyNames = {"userName", "password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProperty(propertyName)) {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProperty(String propertyName) {
        return Arrays.asList(encryptPropertyNames).contains(propertyName);
    }
}
