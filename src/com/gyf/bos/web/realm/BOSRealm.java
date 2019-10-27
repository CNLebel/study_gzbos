package com.gyf.bos.web.realm;

import com.gyf.bos.dao.IUserDao;
import com.gyf.bos.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userDao;

    /**
     * 权限-与角色相关
     * @param principalCollection
     * @return
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("staff");
//        info.addRole("staff");
        return info;
    }

    /**
     * 登录认证
     * @param token
     * @return info
     * @throws AuthenticationException
     * */
    @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //查询数据操作放在realm
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = upToken.getUsername();

        //根据名字查询用户
        User dbUser= userDao.findByUsername(username);
        if (dbUser != null){

            /**
             * Object principal, 把数据库查询的对象
             * Object credentials, 证书： 写密码自动认证，查询出来的密码
             * String realmName, 当前类名
             * */
            //返回AuthenticationInfo对象 [这个对象会自动认证]
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(dbUser,
                            dbUser.getPassword(),
                            this.getClass().getSimpleName());
            return info;
        }
        return null;
    }
}
