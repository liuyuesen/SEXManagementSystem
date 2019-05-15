package com.example.demo.Login;

import com.example.demo.tool.DBManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@RestController
@CrossOrigin
@ResponseBody
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        String userName = loginRequest.getId();
        String password = loginRequest.getPasswords();
        LoginResponse loginResponse = new LoginResponse();
        String sql = "SELECT password FROM teachers where account = " + userName + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            String DBpassword = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                DBpassword = result.getString("password");
            }
            if (DBpassword.equals(password)) {
                result.close();
                dbManager.close();
                loginResponse.setResult(true);
                return loginResponse;
            }
        } catch (Exception e) {
            loginResponse.setResult(false);
            loginResponse.setStatus(e.toString());
            return loginResponse;
        }
        loginResponse.setResult(false);
        loginResponse.setStatus("密码错误");
        return loginResponse;
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
    private String id;
    private String passwords;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
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
