package com.example.demo.Login;

import com.example.demo.tool.DBManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

@RestController
@CrossOrigin
@ResponseBody
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginBean login(@RequestBody JSs js) {
        String userName = js.getId();
        String password = js.getPasswords();
        LoginBean loginBean = new LoginBean();
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
                loginBean.setResult(true);
                return loginBean;
            }
        } catch (Exception e) {
            loginBean.setResult(false);
            loginBean.setStatus(e.toString());
            return loginBean;
        }
        loginBean.setResult(false);
        loginBean.setStatus("密码错误");
        return loginBean;
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

class JSs {
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

class LoginBean {
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
