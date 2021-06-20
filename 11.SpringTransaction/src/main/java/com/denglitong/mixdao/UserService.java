package com.denglitong.mixdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/19
 */
@Service("userService")
@Controller
public class UserService extends BaseService {

    private HibernateTemplate hibernateTemplate;

    private CreditsService creditsService;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Autowired
    public void setCreditsService(CreditsService creditsService) {
        this.creditsService = creditsService;
    }

    @RequestMapping("/logon3.do")
    public void logon(String userName) {
        // 通过 hibernate 技术访问
        System.out.println("before userService.updateLastVisit...");
        updateLastVisit(userName);
        System.out.println("after userService.updateLastVisit...");

        // 通过 JDBC 技术访问
        System.out.println("before creditsService.addCredits...");
        creditsService.addCredits(userName, 20);
        System.out.println("after creditsService.addCredits...");
    }

    public void updateLastVisit(String userName) {
        User user = hibernateTemplate.get(User.class, userName);
        user.setLastVisit(new Date());
        hibernateTemplate.update(user);
        // 默认情况下，hibernate 对数据的更改只记录在一级缓存中，要等到事务提交或显式调用 flush()
        // 才将一级缓存中的数据同步到数据库，而提交事务的操作发生在事务方法（logon）返回之前
        // 这种数据同步的延迟机制在只有一种访问数据库的方式时没有问题（都使用 Hibernate）
        // 但是当 hibernate 和其他访问技术结合使用时（如 jdbc），jdbc 感知不到 hibernate 的一级缓存
        // 所有需要显式调用 flush() 刷新到数据库
        hibernateTemplate.flush();
    }
}
