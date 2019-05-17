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
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
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
            if (loginArray.isEmpty()) {
                loginResponse.setResult(false);
                loginResponse.setStatus("账号不存在");
                return loginResponse;
            } else if (loginArray.getJSONObject(0).get("password").equals(password)) {
                loginResponse.setResult(true);
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
}

class LoginRequest {
    private String account;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }

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

    public boolean equals(LoginRequest loginRequest) {
        if ((this.account.equals(loginRequest.account)) && (this.password.equals(loginRequest.password))) {
            return true;
        } else {
            return false;
        }
    }
}

class LoginResponse extends Object {
    private boolean result;
    private String status;

    public LoginResponse() {
    }

    public LoginResponse(boolean result, String status) {
        this.result = result;
        this.status = status;
    }

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


    public boolean equals(LoginResponse loginResponse) {
        if (this.result == loginResponse.result && this.status == null) {
            return true;
        } else if ((this.result == loginResponse.result) && (this.status.equals(loginResponse.status))) {
            return true;
        } else {
            return false;
        }
    }
}
