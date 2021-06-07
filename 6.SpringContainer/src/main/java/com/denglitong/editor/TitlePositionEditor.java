package com.denglitong.editor;

import java.beans.PropertyEditorSupport;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/5
 */
public class TitlePositionEditor extends PropertyEditorSupport {

    private String[] options = {"Left", "Center", "Right"};

    /**
     * 代表可选属性值的字符串标识数组
     *
     * @return
     */
    @Override
    public String[] getTags() {
        return options;
    }

    /**
     * 代表属性初始值的字符串
     *
     * @return
     */
    @Override
    public String getJavaInitializationString() {
        return "" + getValue();
    }

    /**
     * 将内部属性值转换为字符串标识形式，供属性编辑器展示之用
     *
     * @return
     */
    @Override
    public String getAsText() {
        int value = (int)getValue();
        return options[value];
    }

    /**
     * 将外部设置的字符串转换为内部属性的值
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(text)) {
                setValue(i);
                return;
            }
        }
    }
}
