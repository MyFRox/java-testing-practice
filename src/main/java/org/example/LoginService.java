package org.example;

public class LoginService {
    private UserDao userDao;
    public LoginService(UserDao userDao){
        this.userDao =userDao;
    }
    public boolean login(String name , String password) {
        User user = userDao.findByName(name);
        if (user != null && user.getPassword().equals(password)){
            userDao.updateLastLoginTime(user.getUsername());  // 登录成功时更新最后登录时间
            return true;
        }else {
            return false;
        }

    }
}
