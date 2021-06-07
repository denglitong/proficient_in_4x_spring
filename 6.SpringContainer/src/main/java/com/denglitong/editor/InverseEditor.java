package com.denglitong.editor;

import java.beans.PropertyEditorSupport;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class InverseEditor extends PropertyEditorSupport {

    private String[] options = {"true", "false"};

    @Override
    public String[] getTags() {
        return options;
    }

    @Override
    public String getJavaInitializationString() {
        return "" + getValue();
    }

    @Override
    public String getAsText() {
        int value = (int)getValue();
        return options[value];
    }

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
