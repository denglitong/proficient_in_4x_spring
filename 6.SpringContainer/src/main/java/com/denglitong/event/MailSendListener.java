package com.denglitong.event;

import org.springframework.context.ApplicationListener;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/7
 */
public class MailSendListener implements ApplicationListener<MailSendEvent> {

    @Override
    public void onApplicationEvent(MailSendEvent event) {
        System.out.println("MailSendListener:向" + event.getTo() + "发送完一封邮件");
    }
}
