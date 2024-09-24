package com.yj.reservation.controller;

import com.yj.reservation.business.LoginBusiness;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.bean.LoginReq;
import com.yj.reservation.common.security.bean.LoginParam;
import com.yj.reservation.common.security.constant.SecurityConst;
import com.yj.reservation.common.util.CookieUtil;
import com.yj.reservation.common.util.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginBusiness loginBusiness;

    /**
     * web客户端后台登录接口
     *      分子光谱快速预测分析平台
     * @param loginParam
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginParam loginParam) {
        System.out.println("进入登录");
        return loginBusiness.login(loginParam.getUsername(), loginParam.getPassword());
    }

//    /**
//     * 设备终端登录接口
//     * @return
//     */
//    @PostMapping("/deviceLogin")
//    public JsonResult deviceLogin(@RequestBody DeviceLoginDto dto){
//        return loginBusiness.deviceLogin(dto);
//    }

    /**
     * Web登录退出
     * @return
     */
    @GetMapping("/loginout")
    public JsonResult logout() {
//        CookieUtil.clearCookie(SecurityConst.TOEKN_COOKIE_NAME, response);
//        return JsonResult.success().setMsg("退出成功!");

        LoggerUtil.info("进入退出登录");
        return loginBusiness.logout();
    }


    @GetMapping("test")
//    @PreAuthorize("hasRole('quanxianCode')") //SpringSecurity添加的权限代码 并且可以支持表达式处理
    public JsonResult test(){// @PreAuthorize("hasRole('quanxianCode') and authentication.name == 'admin'") 表示：当前用户必须是有quanxianCode并且用户名是admin才能访问

        //如何通过Security获取登录用户信息
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        Object principal = authentication.getPrincipal();//获取用户的身份信息
//        Object credentials = authentication.getCredentials();//脱敏
//        Collection authorities = authentication.getAuthorities();//授权信息


        return JsonResult.success();
    }

//    private final AuthenticationManager authenticationManager;
//    @PostMapping("/sys/login")
//    public JsonResult login(LoginReq req){
//        String account = req.getAccount();
//        String pwd = req.getPwd();
//        String code = req.getCode();
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(account, pwd);
//
////        Authentication auth =
//
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
////        return authentication.getPrincipal();
//
//
//        JsonResult result = JsonResult.success();
//        result.getData().put("principal", authentication.getPrincipal());
//        result.getData().put("credentials", authentication.getCredentials());
//        result.getData().put("authorities", authentication.getAuthorities());
//        result.getData().put("details", authentication.getDetails());
//        return result;
//    }
}
