package com.denglitong.editor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class ChartBeanBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // 将 TitlePositionEditor 绑定到 CharBean 的 titlePosition 属性上
            PropertyDescriptor titlePositionDescriptor = new PropertyDescriptor("titlePosition", ChartBean.class);
            titlePositionDescriptor.setPropertyEditorClass(TitlePositionEditor.class);

            PropertyDescriptor inverseDescriptor = new PropertyDescriptor("inverse", ChartBean.class);
            inverseDescriptor.setPropertyEditorClass(ChartBean.class);

            return new PropertyDescriptor[]{titlePositionDescriptor, inverseDescriptor};
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
