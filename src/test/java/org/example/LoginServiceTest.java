package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    LoginService loginService;
    @Mock UserDao userDao;

    @BeforeEach
    void setUp() {
        loginService = new LoginService(userDao);


    }



    @Test
    @DisplayName("TC-001 用户名不存在，登录失败")
    void loginUserNotExist() {
        assertFalse(loginService.login("notexist", "Abc12345"));
    }

    @Test
    @DisplayName("TC-002 密码错误，登录失败")
    void loginWrongPassword() {
        when(userDao.findByName("zhangsan")).thenReturn(new User("zhangsan", "Abc12345"));
        assertFalse(loginService.login("zhangsan", "wrongpass"));
    }

    @Test
    @DisplayName("TC-003 用户名密码正确，登录成功")
    void loginSuccess() {
        when(userDao.findByName("zhangsan")).thenReturn(new User("zhangsan", "Abc12345"));
        assertTrue(loginService.login("zhangsan", "Abc12345"));
        verify(userDao).updateLastLoginTime("zhangsan");
    }


    @Test
    @DisplayName("TC-004 查询用户时数据库异常，登录抛异常")
    void loginWhenDaoThrows() {
        when(userDao.findByName("zhangsan")).thenThrow(new RuntimeException("数据库连接失败"));
        assertThrows(RuntimeException.class, () -> loginService.login("zhangsan", "Abc12345"));
    }



    @Test
    @DisplayName("TC-005 登录失败时不更新最后登录时间")
    void loginFailShouldNotUpdateLastLoginTime() {
        when(userDao.findByName("zhangsan")).thenReturn(new User("zhangsan", "Abc12345"));
        loginService.login("zhangsan", "wrongpass");
        verify(userDao, never()).updateLastLoginTime(anyString());
    }
}