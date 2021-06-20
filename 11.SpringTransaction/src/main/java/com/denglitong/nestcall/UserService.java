package com.denglitong.nestcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/19
 */
@Service("userService")
@Controller
public class UserService extends BaseService {

    private JdbcTemplate jdbcTemplate;

    private CreditsService creditsService;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setCreditsService(CreditsService creditsService) {
        this.creditsService = creditsService;
    }

    @RequestMapping("/logon2.do")
    public void logon(String userName) {
        System.out.println("before userService.updateLastVisit...");
        updateLastVisit(userName);
        System.out.println("after userService.updateLastVisit...");

        // 在同一个线程中调用，加入当前事务
        // System.out.println("before creditsService.addCredits...");
        // creditsService.addCredits(userName, 20);
        // System.out.println("after creditsService.addCredits...");

        // 在一个新线程中调用，开启新事务
        MyThread myThread = new MyThread(this.creditsService, userName, 20);
        myThread.start();
    }

    public void updateLastVisit(String userName) {
        String sql = "UPDATE t_user SET last_visit = ? WHERE user_name = ?";
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        jdbcTemplate.update(sql, now, userName);
    }

    private class MyThread extends Thread {
        private CreditsService creditsService;
        private String userName;
        private int toAdd;

        public MyThread(CreditsService creditsService, String userName, int toAdd) {
            this.creditsService = creditsService;
            this.userName = userName;
            this.toAdd = toAdd;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("before creditsService.addCredits...");
            creditsService.addCredits(userName, toAdd);
            System.out.println("after creditsService.addCredits...");
        }
    }
}
