package com.denglitong.editor;

import java.beans.PropertyEditorSupport;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class CarEditor extends PropertyEditorSupport {

    /**
     * 将字面值转换为属性类型对象
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || !text.contains(",")) {
            throw new IllegalArgumentException("设置的字符串格式不正确");
        }
        String[] attrs = text.split(",");
        Car car = new Car();
        car.setBrand(attrs[0]);
        car.setColor(attrs[1]);
        car.setMaxSpeed(Integer.parseInt(attrs[2]));
        setValue(car);
    }
}
