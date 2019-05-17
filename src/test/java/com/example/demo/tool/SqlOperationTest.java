package com.example.demo.tool;

import com.example.demo.Login.LoginController;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.testng.Assert.*;

@ContextConfiguration(classes = SQLOperation.class)
public class SqlOperationTest extends AbstractTestNGSpringContextTests {

}