package com.yj.reservation.common.security.bean;

import lombok.Data;

@Data
public class LoginInfo {

    public static final Integer SYS_TAG_SYSTEM = 1;
    public static final Integer SYS_TAG_ORGANIZATION = 2;
    public static final Integer SYS_TAG_USER = 3;
    public static final Integer SYS_TAG_APP = 9;

    private String logKey;

    private Long userId;
    private String userName;
    /*
    sysTag: 进行判断处理
        如果是sys / 就是后台登录用户 也就是查询：mm_sys_user表
        如果是app / 就是前端登录用户 也就是查询：mm_user表
     */
    private String sysTag;// sysTag = user表的 type

    private Long organizationId;
    private String organizationIdSeq;
    private String organizationName;

    private String industryCode;
    private String industryName;

    public String createSysTagRole(){
        if(eqSystem()){
            return "sys";
        }else if(eqOrgSystem()){
            return "sysOrg";
        }else if(eqApp()) {
            return "app";
        }else{
            return "user";
        }
    }

    /**
     * 判断是平台管理员
     * @return
     */
    public boolean eqSystem(){
        return SYS_TAG_SYSTEM.equals(sysTag);
    }

    /**
     * 判断不是平台管理员
     * @return
     */
    public boolean notSystem(){
        return SYS_TAG_ORGANIZATION.equals(sysTag) || SYS_TAG_USER.equals(sysTag);
    }

    /**
     * 判断是公司管理员
     * @return
     */
    public boolean eqOrgSystem(){
        return SYS_TAG_ORGANIZATION.equals(sysTag);
    }

    /**
     * 判断不是公司管理员
     * @return
     */
    public boolean notOrgSystem(){
        return SYS_TAG_SYSTEM.equals(sysTag) || SYS_TAG_USER.equals(sysTag);
    }
    /**
     * 判断是普通用户
     * @return
     */
    public boolean eqUser(){
        return SYS_TAG_USER.equals(sysTag);
    }
    /**
     * 判断不是普通用户
     * @return
     */
    public boolean notUser(){
        return SYS_TAG_SYSTEM.equals(sysTag) || SYS_TAG_ORGANIZATION.equals(sysTag);
    }

    /**
     * 判断是App用户
     * @return
     */
    public boolean eqApp(){
        return SYS_TAG_APP.equals(sysTag);
    }
    /**
     * 判断不是App用户
     * @return
     */
    public boolean notApp(){
        return !SYS_TAG_APP.equals(sysTag);
    }
}
