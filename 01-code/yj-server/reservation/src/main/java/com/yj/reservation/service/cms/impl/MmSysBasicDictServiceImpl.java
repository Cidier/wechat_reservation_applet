package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmSysBasicDict;
import com.yj.reservation.mapper.cms.MmSysBasicDictMapper;
import com.yj.reservation.pojo.cms.vo.BasicDictVO;
import com.yj.reservation.service.cms.MmSysBasicDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysBasicDictQuery;
import com.yj.reservation.pojo.cms.dto.MmSysBasicDictDTO;
import com.yj.reservation.pojo.cms.vo.MmSysBasicDictVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysBasicDictServiceImpl extends ServiceImpl<MmSysBasicDictMapper, MmSysBasicDict>
        implements MmSysBasicDictService {

    /**
    * 新增 字典表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysBasicDict(MmSysBasicDictDTO dto){
        MmSysBasicDict entity = new MmSysBasicDict();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 字典表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysBasicDict(MmSysBasicDictDTO dto){
        MmSysBasicDict entity = new MmSysBasicDict();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 字典表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysBasicDict(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 字典表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysBasicDictVO queryMmSysBasicDictById(Long id){
        MmSysBasicDict entity = super.getById(id);
        MmSysBasicDictVO vo = new MmSysBasicDictVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 字典表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysBasicDictVO> pageList(MmSysBasicDictQuery query) {
        Page<MmSysBasicDict> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysBasicDict>());
        List<MmSysBasicDict> list = page.getRecords();
        List<MmSysBasicDictVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysBasicDictVO vo = new MmSysBasicDictVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysBasicDictVO>().setTotal(page.getTotal()).setRecords(resultList);
    }



    @Override
    public List<BasicDictVO> list(Long parentId) {
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();

        if(parentId != null){
            queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
        }else{
            queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
        }

        return getBasicDictVOS(queryWrapper);
    }


    @Override
    public List<BasicDictVO> listByDictKeyBox(String dictKey) {
        MmSysBasicDict baseBean = findByDictKey(null, dictKey);
        if(baseBean == null){
            return new ArrayList<>();
        }
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysBasicDict.PARENT_ID, baseBean.getId());
        return getBasicDictVOS(queryWrapper);
    }

    public MmSysBasicDict findByDictKey(Long parentId, String dictKey){
        return findByDictKey(parentId, dictKey, true);
    }

    @Override
    public MmSysBasicDict findByDictKey(String dictKey) {

        return findByDictKey(null, dictKey, false);
    }

    public MmSysBasicDict findByDictKey(Long parentId, String dictKey, boolean isParentId){
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        if(isParentId){
            if(parentId != null){
                queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
            }else{
                queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
            }
        }

        queryWrapper.eq(MmSysBasicDict.DICT_KEY, dictKey);
        List<MmSysBasicDict> list = baseMapper.selectList(queryWrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    private List<BasicDictVO> getBasicDictVOS(QueryWrapper<MmSysBasicDict> queryWrapper) {
        List<MmSysBasicDict> dictList = list(queryWrapper);
        List<BasicDictVO> resultList = new ArrayList<>();
        dictList.forEach(bean->{
            BasicDictVO vo = new BasicDictVO();
            BeanUtils.copyProperties(bean, vo);
            if(getParentChildCount(vo.getId()) > 0){
                vo.setHasChild(true);
            }
            resultList.add(vo);
        });

        return resultList;
    }

    public Long getParentChildCount(Long parentId){
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        if(parentId != null){
            queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
        }else{
            queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
        }
        return baseMapper.selectCount(queryWrapper);
    }
}
