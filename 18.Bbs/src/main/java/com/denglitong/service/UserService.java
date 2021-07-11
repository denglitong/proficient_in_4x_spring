package com.denglitong.service;

import com.denglitong.dao.LoginLogDao;
import com.denglitong.dao.UserDao;
import com.denglitong.domain.LoginLog;
import com.denglitong.domain.User;
import com.denglitong.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/7/2
 */
@Service
@Transactional
public class UserService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    /**
     * 注册一个新用户，如果用户名已存在则抛出 UserExistException
     *
     * @param user
     * @throws UserExistException
     */
    public void register(User user) throws UserExistException {
        User u = userDao.getUserByUserName(user.getUserName());
        if (u != null) {
            throw new UserExistException("用户名已存在:" + user.getUserName());
        } else {
            user.setCredit(100);
            user.setUserType(User.NORMAL_USER);
            userDao.save(user);
        }
    }

    // 更新用户
    public void update(User user) {
        userDao.update(user);
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    public User getUserById(int userId) {
        return userDao.get(userId);
    }

    public void lockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_LOCK);
        userDao.update(user);
    }

    public void unLockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_UNLOCK);
        userDao.update(user);
    }

    public List<User> queryUserByUserName(String userName) {
        return userDao.queryUserByUserName(userName);
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> getAllUsers() {
        return userDao.loadAll();
    }

    /**
     * 登录成功
     *
     * @param user
     */
    public void loginSuccess(User user) {
        user.setCredit(user.getCredit() + 5);
        userDao.update(user);

        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDateTime(new Date());
        loginLogDao.save(loginLog);
    }
}
