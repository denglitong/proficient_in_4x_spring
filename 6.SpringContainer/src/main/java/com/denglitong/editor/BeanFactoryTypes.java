package com.denglitong.editor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstantiationStrategy;

import java.beans.*;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/5
 */
public class BeanFactoryTypes {

    public static void main(String[] args) {
        // Bean 实例化涉及到的一些接口和实现类
        BeanDefinition beanDefinition;
        InstantiationStrategy instantiationStrategy;
        BeanWrapper beanWrapper;
        PropertyEditor propertyEditor;
        PropertyEditorSupport propertyEditorSupport;
        BeanInfo beanInfo;
        PropertyDescriptor propertyDescriptor;
        SimpleBeanInfo simpleBeanInfo;
        PropertyEditorRegistrySupport propertyEditorRegistrySupport;
    }
}
