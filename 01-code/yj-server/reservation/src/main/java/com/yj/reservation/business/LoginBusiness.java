package com.yj.reservation.business;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.constant.charset.RedisKeysConst;
import com.yj.reservation.common.redis.RedisService;
import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.util.HexTokenUtil;
import com.yj.reservation.common.security.util.LoginInfoUtil;
import com.yj.reservation.common.util.JsonUtil;
import com.yj.reservation.common.util.LoggerUtil;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysTenant;
import com.yj.reservation.entity.cms.MmSysUser;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.pojo.cms.vo.MenuVO;
import com.yj.reservation.service.cms.MmSysPermissionService;
import com.yj.reservation.service.cms.MmSysTenantService;
import com.yj.reservation.service.cms.MmSysUserRoleRelationService;
import com.yj.reservation.service.cms.impl.MmSysUserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class LoginBusiness {

    @Autowired
    private MmSysUserServiceImpl userService;
    @Autowired
    private MmSysUserRoleRelationService userRoleRelationService;
    @Autowired
    private MmSysPermissionService permissionService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MmSysTenantService tenantService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 平台登录
     *      代码优化：
     *          Redis缓存优化 优化相关的存储
     * @param username
     * @param password
     * @return
     */
    public JsonResult login(String username, String password){
        /*
         * 登录后：
         *  1.用户个人信息
         *  2.用户公司信息
         *  3.用户权限信息
         */
        LoggerUtil.info("username:" + username + " password:" + password);
        MmSysUser user = userService.getByUserName(username);
        if (!isUserPwd(password, user)) return JsonResult.fail().setMsg("用户名或密码错误!");
        //生成token数据，并放入redis中
        try {
            //查询权限信息
            List<MmSysPermission> permissionList;
            if (user.getId() == 1) {
                permissionList = permissionService.list();
            } else {
                List<MmSysUserRoleRelation> userRoleRelList = userRoleRelationService.listByUserId(user.getId());
//                permissionList = permissionService.listByUserRoleRelation(userRoleRelList);
                permissionList = permissionService.listByUserRoleReletionSql(userRoleRelList);
            }
            Set<MmSysPermission> permissionSet = new HashSet<>(permissionList);

            //查询菜单
            List<MenuVO> menuList = permissionService.listToMenuVO(permissionSet);

            LoginInfo loginInfo = cacheUserInfo(user);

            //redis 存储 权限信息
            String redisKey = HexTokenUtil.getRedisTkUserId(loginInfo.getLogKey());
            String encryptToken = HexTokenUtil.encryptToken(redisKey);
            redisService.set(redisKey, JsonUtil.toJsonString(permissionSet), 86400l);// 6小时21600 24 86400



            Map<String, Object> map = new HashMap<>();
            map.put("token", encryptToken);
            map.put("uid", user.getId());
            map.put("avatar", "static/img/avatar.jpg");
            map.put("username", user.getName());
            map.put("role", loginInfo.createSysTagRole());
//            map.put("organizationName", loginInfo.getOrganizationName());
//            map.put("industryName", loginInfo.getIndustryName());
//            return JsonResult.success().put("userInfo", map).put("permissionList", permissionSet);
            return JsonResult.success().put("userInfo", map).put("menuList", menuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.fail().setMsg("服务器异常");
    }

    public JsonResult logout()  {
        LoginInfo loginInfo = LoginInfoUtil.getCurrent();
        if(loginInfo == null){
            return JsonResult.fail().setMsg("用户已退出");
        }
        //登录权限清除
        String redisKey = HexTokenUtil.getRedisTkUserId(loginInfo.getLogKey());
        redisService.delete(redisKey);
        //登录用户清除
        redisService.delete(RedisKeysConst.toLoginKey(loginInfo.getUserId()));
        redisService.delete(loginInfo.getLogKey());

        return JsonResult.success().setMsg("退出成功!");
    }


    private LoginInfo cacheUserInfo(MmSysUser user) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(user.getId());
        loginInfo.setUserName(user.getUserName());
        loginInfo.setSysTag(user.getType());

        //租户本身就是公司，所以这里需要获取租户ID和名称

        MmSysTenant tenant = tenantService.getById(user.getTenantId());
        if(tenant != null){
//        loginInfo.setTenantId(tenant.getId());
            loginInfo.setOrganizationId(tenant.getId());
//        loginInfo.setOrganizationIdSeq(user.getDeptId());
            loginInfo.setOrganizationName(tenant.getTenantName());
        }

//        IndustryVO industryVO = industryService.getById(organization.getIndustryId());
//        loginInfo.setIndustryCode(industryVO.getCode());
//        loginInfo.setIndustryName(industryVO.getName());

        //redis 存储 login数据
        redisService.set(RedisKeysConst.toLoginKey(user.getId()), JsonUtil.toJsonString(loginInfo), 86400l);// 6小时21600 24 86400
        LoginInfoUtil.add(loginInfo);
        return loginInfo;
    }



//    public JsonResult deviceLogin(@RequestBody DeviceLoginDto dto){
//        if(StringUtils.isEmpty(dto.getUsername()) || StringUtils.isEmpty(dto.getPassword())
//                ||StringUtils.isEmpty(dto.getDeviceNo())){
//            return JsonResult.fail().setMsg("参数错误");
//        }
//
//        SysUser user = userService.getByUserNameMapper(dto.getUsername());
//        if (isUserPwd(dto.getPassword(), user)) return JsonResult.fail().setMsg("用户名或密码错误!");
//
//        UserOrgDeviceVO vo = new UserOrgDeviceVO();
//        vo.setId(user.getId());
//        vo.setName(user.getName());
//        vo.setNickName(user.getNickName());
//        vo.setUserName(user.getUserName());
//
//        return JsonResult.success().put("uinfo", vo);
//    }

    public boolean isUserPwd(String password, MmSysUser user) {
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getUserPassword());
    }

    public String genPassword(String password){
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

        String encode = passwordEncoder.encode("123456");

        System.out.println(encode);

        System.out.println(passwordEncoder.matches("123123", encode));
        System.out.println(passwordEncoder.matches("123123", "$2a$04$ydWeeZl5i/lM3QeAgGdc/O0L02/eSXU7LR6RkFEILu6ScPbC3c1ba"));
    }

}
