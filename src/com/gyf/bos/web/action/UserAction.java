package com.gyf.bos.web.action;

import com.gyf.bos.model.User;
import com.gyf.bos.service.IUserService;
import com.gyf.bos.utils.MD5Utils;
import com.gyf.bos.web.action.base.BaseAction;
import org.activiti.engine.RepositoryService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends BaseAction<User> {

    //创建了一个日志对象
    Logger logger = Logger.getLogger(UserAction.class);

    @Autowired
    private RepositoryService rs;

    public String login(){

        logger.info(getModel());

        System.out.println(rs);

        // 获取参数
        String username = getModel().getUsername();
        String password = getModel().getPassword();

        //request
        HttpServletRequest request = ServletActionContext.getRequest();
        String serverCheckCode = (String) request.getSession().getAttribute("key");
        String clientCheckCode = request.getParameter("checkcode");

        if (serverCheckCode.equalsIgnoreCase(clientCheckCode)){  //验证码正确

            /**
             * 使用shiro,就不再使用userService的login方法登录
             * 而是使用Subject的login方法
             * */

            // 获取一个subject
            Subject subject = SecurityUtils.getSubject();

            // 创建一个Token，这个对象存着用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.text2md5(password));
            try {
                subject.login(token);

                // 登录成功 [把用户对象在session]
                User loginUser = (User) subject.getPrincipal();
                subject.getSession().setAttribute("loginUser", loginUser);

                return "home";
            } catch (AbstractMethodError e){
                e.printStackTrace();    //登录失败会抛出异常
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
