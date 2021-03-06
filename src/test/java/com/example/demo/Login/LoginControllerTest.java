package com.example.demo.Login;

import com.example.demo.tool.SQLOperation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.assertj.core.api.Assertions.*;


@ContextConfiguration(classes = SQLOperation.class)
public class LoginControllerTest  extends AbstractTestNGSpringContextTests {
    LoginController loginController = new LoginController();
    @Test
    public void testLoginRight() throws Exception {
        assertEquals(loginController.login(new LoginRequest("1301913704","980508Izwh")).equals( new LoginResponse(true,null)),true);
    }

    @Test
    public void testLoginPasswordFall () throws Exception{
        assertEquals(loginController.login(new LoginRequest("1301913704","123456")).equals(new LoginResponse(false,"密码错误")), true);
    }

    @Test
    public void testLoginAccountFall () throws Exception{
//        assertEquals(loginController.login(new LoginRequest("123456","123456")).equals(new LoginResponse(false,"账号不存在")), true);
        assertThat(loginController.login(new LoginRequest("123456","123456"))).isEqualToComparingFieldByFieldRecursively(new LoginResponse(false,"账号不存在"));
    }
}