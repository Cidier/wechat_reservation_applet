package com.yj.reservation.controller.cms;

import com.yj.reservation.business.SysUserBusiness;
import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.util.LoginInfoUtil;
import com.yj.reservation.entity.cms.MmSysUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysUserQuery;
import com.yj.reservation.pojo.cms.dto.MmSysUserDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysUserService;

import java.util.List;
/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "用户表 API", tags = "用户表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-user")
public class MmSysUserController {

    private final MmSysUserService service;
    private final SysUserBusiness business;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation("新增 用户表")
    @PostMapping
    public JsonResult addMmSysUser(@Valid @RequestBody MmSysUserDTO dto) {
        service.addMmSysUser(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 用户表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysUser(@PathVariable Long id, @Valid @RequestBody MmSysUserDTO dto) {
        dto.setId(id);
        service.modifyMmSysUser(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 用户表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysUser(@PathVariable Long id) {
        business.delete(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 用户表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysUserById(@PathVariable Long id) {
        MmSysUserVO vo = service.queryMmSysUserById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 用户表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysUserQuery query) {
        Page<MmSysUserVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPWD/{id}")
//  @Security(code="system:user:resetPWD")
    public JsonResult resetPWD(@PathVariable Long id) {
        business.resetPWD(id);
        return JsonResult.success();
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//  @Security(code="system:user:insert")
    public JsonResult save(@RequestBody MmSysUserDTO dto) {
        return business.save(dto);
    }

//    @PostMapping("upUserOrg")
//    public JsonResult upUserOrg(UserInfoVO vo){
//
//        return business.upUserOrg(loginInfo, vo);
//    }

    @PostMapping("/changePassword")
    public JsonResult changePassword(String oldPassword, String newPassword) throws Exception {
        LoginInfo loginInfo = LoginInfoUtil.getCurrent();
        MmSysUser u = service.getById(loginInfo.getUserId());
        //重置密码
        if (passwordEncoder.matches(newPassword, u.getUserPassword())) {
            return JsonResult.fail().setMsg("新密码与原密码不能重复！");
        } else if (passwordEncoder.matches(oldPassword, u.getUserPassword())) {
            u.setUserPassword(passwordEncoder.encode(newPassword));
            service.updateById(u);
            return JsonResult.success();
        }
        return JsonResult.fail().setMsg("原密码输入错误！");
    }

    /**
     * 根据userId查询中间表
     */
    @GetMapping("/userRoleRelationById")
//  @Security(code="system:user:update")
    public JsonResult userRoleRelationById(@RequestParam("userId") Long userId) {
        return JsonResult.success().put("bean", business.userRoleRelationById(userId));
    }

}
