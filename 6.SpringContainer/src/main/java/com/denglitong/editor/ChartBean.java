package com.denglitong.editor;

import javax.swing.*;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/5
 */
public class ChartBean extends JPanel {

    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;

    private int titlePosition = CENTER;
    private boolean inverse;

    public int getTitlePosition() {
        return titlePosition;
    }

    public void setTitlePosition(int titlePosition) {
        this.titlePosition = titlePosition;
    }

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }
}
