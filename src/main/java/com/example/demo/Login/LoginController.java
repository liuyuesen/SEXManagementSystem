package com.example.demo.Login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.tool.SQLOperation;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.sql.ResultSet;

@RestController
@CrossOrigin
@ResponseBody
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        String account = loginRequest.getAccount();
        String password = loginRequest.getPassword();
        LoginResponse loginResponse = new LoginResponse();
        String sql = "SELECT password FROM teachers where account = " + account + ";";
        JSONArray loginArray = new JSONArray();
        JSONObject object = new JSONObject();
        JSONObject jsonResponse = new JSONObject();
        object.put("account", 123456);
        try {
            loginArray = SQLOperation.select(sql);
            if (loginArray.getJSONObject(0).get("password").equals(password)) {
                loginResponse.setResult(true);
                return loginResponse;
            } else if (loginArray.getJSONObject(0).get("password") == null) {
                loginResponse.setResult(false);
                loginResponse.setStatus("账号不存在");
                return loginResponse;
            } else {
                loginResponse.setResult(false);
                loginResponse.setStatus("密码错误");
                return loginResponse;
            }
        } catch (Exception e) {
            loginResponse.setResult(false);
            loginResponse.setStatus(e.toString());
            return loginResponse;
        }
    }

    private String getStatus(int number) {
        switch (number) {
            case 0:
                return "staff";
            case 1:
                return "department_manager";
            case 2:
                return "deputy_general_manager";
            case 3:
                return "general_manager";
            default:
                return "error";
        }
    }
}

class LoginRequest {
    private String account;
    private String password;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginResponse {
    private boolean result;
    private String status;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean getResult() {
        return result;
    }

}
