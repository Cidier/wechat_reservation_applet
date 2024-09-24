package com.yj.reservation.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.util.LoginInfoUtil;
import com.yj.reservation.entity.cms.MmSysUser;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.mapper.cms.MmSysUserMapper;
import com.yj.reservation.mapper.cms.MmSysUserRoleRelationMapper;
import com.yj.reservation.pojo.cms.dto.MmSysUserDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserVO;
import com.yj.reservation.service.cms.MmSysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserBusiness {
    private final MmSysUserService service;
    private final MmSysUserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final MmSysUserRoleRelationMapper userRoleRelationMapper;



    //删除
    @Transactional
    public JsonResult delete(Long id) {
        //删除时注意，不能删除自己
        LoginInfo loginInfo = LoginInfoUtil.getCurrent();
        if(loginInfo == null || loginInfo.getUserId().equals(id)){
            return JsonResult.fail().setMsg("用户无权限删除自己!");
        }
        MmSysUser user = service.getById(id);
        if(user == null){
            return JsonResult.fail().setMsg("当前用户无权限删除!");
        }
        mapper.deleteById(id);
        QueryWrapper<MmSysUserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper();
        userRoleRelationQueryWrapper.eq(MmSysUserRoleRelation.USER_ID, id);
        userRoleRelationMapper.delete(userRoleRelationQueryWrapper);
        return JsonResult.success();
    }

    //修改密码
    @Transactional
    public void resetPWD(Long id){
        MmSysUser user = mapper.selectById(id);
        String password = passwordEncoder.encode("123456");
        user.setUserPassword(password);
        mapper.updateById(user);
    }

    @Transactional
    public JsonResult save(MmSysUserDTO dto) {
        LoginInfo loginInfo = LoginInfoUtil.getCurrent();
        MmSysUser user = getByUserName(dto.getUserName());
        if(user != null && user.getId() == null){
            return JsonResult.fail().setMsg("用户已存在!");
        }
        if (dto.getId() != null) {
            //修改
            user = service.getById(dto.getId());
            if(user == null){
                return JsonResult.fail().setMsg("无权限修改该用户或用户不存在!");
            }
            //当前登录用户 不是系统管理员 并且 要修改的管理员
            // 或者 当前用户是普通用户 却要修改 管理员用户或企业管理员用户

            if((loginInfo.notSystem() && user.getType().equals(String.valueOf(LoginInfo.SYS_TAG_SYSTEM)))
                    || (loginInfo.eqUser() && (user.getType().equals(String.valueOf(LoginInfo.SYS_TAG_SYSTEM)) || user.getType().equals(String.valueOf(LoginInfo.SYS_TAG_ORGANIZATION))))){
                return JsonResult.fail().setMsg("当前用户无权限修改该用户!");
            }

//            BeanUtils.copyProperties(dto, user);
            user.setType(dto.getType());
            user.setName(dto.getName());
            user.setState(dto.getState());
            user.setId(dto.getId());
            user.setEmail(dto.getEmail());
            user.setNickName(dto.getNickName());
            user.setRemarks(dto.getRemarks());
            user.setUserName(dto.getUserName());
            mapper.updateById(user);
            QueryWrapper<MmSysUserRoleRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MmSysUserRoleRelation.USER_ID, user.getId());
            userRoleRelationMapper.delete(queryWrapper);
            if(dto.getRoleIds()!=null){insertUserRoleRelation(user, dto);}

        } else {
            //添加
            user = new MmSysUser();

            user.setType(dto.getType());
            user.setName(dto.getName());
            user.setState(dto.getState());
            user.setEmail(dto.getEmail());
            user.setNickName(dto.getNickName());
            user.setRemarks(dto.getRemarks());
            user.setUserName(dto.getUserName());
            user.setUserPassword(passwordEncoder.encode("123456"));
            mapper.insert(user);
            if(dto.getRoleIds()!=null){insertUserRoleRelation(user, dto);}
        }
        return JsonResult.success();
    }
    //添加中间表
    public void insertUserRoleRelation(MmSysUser user, MmSysUserDTO userVO) {
        Long[] ids = userVO.getRoleIds();
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            MmSysUserRoleRelation userRoleRelation = new MmSysUserRoleRelation();
            userRoleRelation.setUserId(user.getId());
            userRoleRelation.setRoleId(id);
            userRoleRelationMapper.insert(userRoleRelation);
        }
    }

    public MmSysUser getByUserName(String username) {
        QueryWrapper<MmSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysUser.USER_NAME, username);
        List<MmSysUser> userList = mapper.selectList(queryWrapper);
        if (userList == null || userList.size() <= 0) {
            return null;
        }
        return userList.get(0);
    }

    //修改弹窗，根据userId 查询的中间表多选框数据
    public Long[] userRoleRelationById(Long userId){
        QueryWrapper<MmSysUserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysUserRoleRelation.USER_ID, userId);
        List<MmSysUserRoleRelation> userRoleRelation=userRoleRelationMapper.selectList(queryWrapper);
        Long[] roleIds = new Long[userRoleRelation.size()];
        for (int i=0;i<userRoleRelation.size();i++){
            roleIds[i]=userRoleRelation.get(i).getRoleId();
        }
        return roleIds;
    }

//    public JsonResult upUserOrg(MmSysUserDTO dto) {
//        MmSysUser user = service.getById(dto.getUserId());
//        if(user == null){
//            return JsonResult.fail().setMsg("当前用户无权限配置公司!");
//        }
////        SysOrganization org = organizationService.getByOrgNo(vo.getOrganizationNo());
////        if(org == null){
////            return JsonResult.fail().setMsg("没有找到对应的公司!");
////        }
////        user.setOrgId(vo.getOrganizationId());
////        user.setOrgIdSeq(org.getOrgIdSeq());
//        this.updateById(user);
//        return JsonResult.success();
//    }
}
