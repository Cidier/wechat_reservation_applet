package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.entity.cms.MmSysUser;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.mapper.cms.MmSysUserMapper;
import com.yj.reservation.mapper.cms.MmSysUserRoleRelationMapper;
import com.yj.reservation.service.cms.MmSysTenantService;
import com.yj.reservation.service.cms.MmSysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysUserQuery;
import com.yj.reservation.pojo.cms.dto.MmSysUserDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysUserServiceImpl extends ServiceImpl<MmSysUserMapper, MmSysUser>
        implements MmSysUserService {

    /**
     * 新增 用户表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysUser(MmSysUserDTO dto){
        MmSysUser entity = new MmSysUser();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
     * 修改 用户表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysUser(MmSysUserDTO dto){
        MmSysUser entity = new MmSysUser();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
     * 删除 用户表
     *
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysUser(Long id){
        super.removeById(id);
    }

    /**
     * 根据id获取 用户表 详情
     *
     * @param id 主键
     */
    @Override
    public MmSysUserVO queryMmSysUserById(Long id){
        MmSysUser entity = super.getById(id);
        MmSysUserVO vo = new MmSysUserVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
     * 分页查询 用户表
     *
     * @param query 参数
     * @return
     */
    @Override
    public Page<MmSysUserVO> pageList(MmSysUserQuery query) {
        Page<MmSysUser> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
                new LambdaQueryWrapper<MmSysUser>());
        List<MmSysUser> list = page.getRecords();
        List<MmSysUserVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysUserVO vo = new MmSysUserVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysUserVO>().setTotal(page.getTotal()).setRecords(resultList);
    }

    @Override
    public MmSysUser loadUserByUsername(String username) {
        QueryWrapper<MmSysUser> queryWrapper = new QueryWrapper<>();
        // 根据用户名称查询用户信息
        queryWrapper.eq(MmSysUser.USER_NAME, username);
        MmSysUser bean = getOne(queryWrapper);
        // users==null登录失败，users！=null登陆成功
        if(bean == null){
            throw new UsernameNotFoundException("登录失败！");
        }
        return bean;
    }

    private final MmSysUserRoleRelationMapper userRoleRelationMapper;
    private final MmSysTenantService tenantService;
    private final PasswordEncoder passwordEncoder;

    //修改弹窗,根据所在行Id查询单条数据
    public MmSysUser getById(Long id){
        QueryWrapper<MmSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysUser.ID, id);
        return baseMapper.selectOne(queryWrapper);
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

    public IPage<MmSysUser> pageList(int pageNo, int pageSize) {
        QueryWrapper<MmSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(MmSysUser.ID);
        IPage<MmSysUser> page = this.page(new Page<>(pageNo, pageSize), queryWrapper);
        return page;
    }

    public MmSysUser getByUserName(String username) {
        QueryWrapper<MmSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysUser.USER_NAME, username);
        List<MmSysUser> userList = baseMapper.selectList(queryWrapper);
        if (userList == null || userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    //删除
    @Transactional
    public JsonResult delete(Long id) {
        //删除时注意，不能删除自己
//        if(loginInfo.getUserId() == id){
//            return JsonResult.fail().setMsg("用户无权限删除自己!");
//        }
        MmSysUser user = getById(id);
        if(user == null){
            return JsonResult.fail().setMsg("当前用户无权限删除!");
        }
        baseMapper.deleteById(id);
        QueryWrapper<MmSysUserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper();
        userRoleRelationQueryWrapper.eq(MmSysUserRoleRelation.USER_ID, id);
        userRoleRelationMapper.delete(userRoleRelationQueryWrapper);
        return JsonResult.success();
    }

    //修改密码
    @Transactional
    public void resetPWD(Long id){
        MmSysUser user=baseMapper.selectById(id);
        //使用强散列加密方式
        String password = passwordEncoder.encode("123456");
//        String password=genPassword("123456", hd_mix).toUpperCase();
        user.setUserPassword(password);
        baseMapper.updateById(user);
    }

    @Transactional
    public JsonResult save(MmSysUserVO userVO) {
        MmSysUser user = getByUserName(userVO.getUserName());
        if(user != null && user.getId() == null){
            return JsonResult.fail().setMsg("用户已存在!");
        }
        if (userVO.getId() != null) {
            //修改
            user = getById(userVO.getId());
            if(user == null){
                return JsonResult.fail().setMsg("无权限修改该用户或用户不存在!");
            }
            //当前登录用户 不是系统管理员 并且 要修改的管理员
            // 或者 当前用户是普通用户 却要修改 管理员用户或企业管理员用户
//            if((loginInfo.notSystem() && user.getType().equals(SystemConst.SYS_TAG_1))
//                    || (loginInfo.eqUser() && (user.getType().equals(SystemConst.SYS_TAG_1) || user.getType().equals(SystemConst.SYS_TAG_2)))){
//                return JsonResult.fail().setMsg("当前用户无权限修改该用户!");
//            }

            user.setType(userVO.getType());
            user.setName(userVO.getName());
            user.setState(userVO.getState());
            user.setId(userVO.getId());
            user.setEmail(userVO.getEmail());
            user.setNickName(userVO.getNickName());
            user.setRemarks(userVO.getRemarks());
            user.setUserName(userVO.getUserName());
            baseMapper.updateById(user);
            QueryWrapper<MmSysUserRoleRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MmSysUserRoleRelation.USER_ID, user.getId());
            userRoleRelationMapper.delete(queryWrapper);
            if(userVO.getRoleIds()!=null){insertUserRoleRelation(user, userVO);}

        } else {
            //添加
            user = new MmSysUser();

            user.setType(userVO.getType());
            user.setName(userVO.getName());
            user.setState(userVO.getState());
            user.setEmail(userVO.getEmail());
            user.setNickName(userVO.getNickName());
            user.setRemarks(userVO.getRemarks());
            user.setUserName(userVO.getUserName());
            String password = passwordEncoder.encode("123456");
//            String password=genPassword("123456", hd_mix).toUpperCase();
            user.setUserPassword(password);
            baseMapper.insert(user);
            if(userVO.getRoleIds()!=null){insertUserRoleRelation(user, userVO);}
        }
        return JsonResult.success();
    }
    //添加中间表
    public void insertUserRoleRelation(MmSysUser user, MmSysUserVO userVO) {
        Long[] ids = userVO.getRoleIds();
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            MmSysUserRoleRelation userRoleRelation = new MmSysUserRoleRelation();
            userRoleRelation.setUserId(user.getId());
            userRoleRelation.setRoleId(id);
            userRoleRelationMapper.insert(userRoleRelation);
        }
    }
}
