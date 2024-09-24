package com.yj.reservation.config.manager;

import com.yj.reservation.entity.cms.MmSysUser;
import com.yj.reservation.service.cms.MmSysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;

public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private MmSysUserService userService;
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 从数据库中获取用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MmSysUser sysUser = userService.loadUserByUsername(username);

        if(sysUser == null){
            throw new UsernameNotFoundException(username);
        }
        System.out.println("查询到了用户，对比校验密码了");
        //权限列表
        Collection<GrantedAuthority> authorities = new ArrayList();

        return new User(sysUser.getUserName(), sysUser.getUserPassword()
                , true //是否启用
                , true //是否过期
                , true //用户凭证是否过期
                , true //用户是否未被锁定
                ,authorities//权限列表
                );
    }
}
