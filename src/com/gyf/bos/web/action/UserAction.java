package com.gyf.bos.web.action;

import com.gyf.bos.model.User;
import com.gyf.bos.service.IUserService;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends BaseAction<User> {

    @Autowired
    private IUserService userService;


    public String login(){
        String username = getModel().getUsername();
        String password = getModel().getPassword();

        //request
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverCheckCode = (String) request.getSession().getAttribute("key");
        String clientCheckCode = request.getParameter("checkcode");
        if (serverCheckCode.equalsIgnoreCase(clientCheckCode)){  //验证码正确

            User user = userService.login(username,password);
            //判断登录状态
            if(user != null){
                System.out.println("登录成功");
                ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
                return "home";  //主页
            } else {
                addActionError("登录失败， 用户名密码不正确");
            }
        } else {
            addActionError("验证码不正确");
        }

        return "loginfailure";
    }



    public String save(){
        return NONE;
    }

    public String delete(){
        return NONE;
    }

    public String update(){
        return NONE;
    }

    public String list(){
        return NONE;
    }

    public String logout() {
        // 把 session 数据清除
        ServletActionContext.getRequest().getSession().invalidate();

        return "login";
    }

    public String editPassword() throws IOException {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        String newPwd = getModel().getPassword();

        User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        String userId = loginUser.getId();

        userService.modifyPassword(newPwd, userId);

        response.setHeader("content-type","text/json;charset=utf-8");
        response.getWriter().print("{\"success\":\"1\"}");



        return NONE;
    }
}
