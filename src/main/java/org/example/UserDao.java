package org.example;

public class UserDao {

    public User findByName(String username) {
        // 真实项目里这里会去查数据库
        // 现在先留空，测试时用 Mockito 替换
        return null;
    }

    public void updateLastLoginTime(String name){

    }
}
