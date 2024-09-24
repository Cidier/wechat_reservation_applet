package com.yj.reservation.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysBasicDict;
import com.yj.reservation.mapper.cms.MmSysBasicDictMapper;
import com.yj.reservation.pojo.cms.vo.BasicDictVO;
import com.yj.reservation.pojo.cms.vo.MmSysBasicDictVO;
import com.yj.reservation.service.cms.MmSysBasicDictService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BasicDictBusiness {

    private final MmSysBasicDictService service;
    private final MmSysBasicDictMapper mapper;

    public IPage<MmSysBasicDict> page(int pageNo, int pageSize, Long parentId) {
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();

        if(parentId != null){
            queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
        }else{
            queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
        }

        return service.page(new Page<>(pageNo, pageSize), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Long id) {
        //删除子级
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysBasicDict.PARENT_ID, id);
        service.remove(queryWrapper);
        //删除当前ID
        return service.removeById(id);
    }

    public List<BasicDictVO> list(Long parentId) {
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();

        if(parentId != null){
            queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
        }else{
            queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
        }

        return getBasicDictVOS(queryWrapper);
    }

    public Long getParentChildCount(Long parentId){
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        if(parentId != null){
            queryWrapper.eq(MmSysBasicDict.PARENT_ID, parentId);
        }else{
            queryWrapper.isNull(MmSysBasicDict.PARENT_ID);
        }
        return mapper.selectCount(queryWrapper);
    }

    public MmSysBasicDict findByDictKey(Long parentId, String dictKey){
        return findByDictKey(parentId, dictKey, true);
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
        List<MmSysBasicDict> list = mapper.selectList(queryWrapper);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public List<BasicDictVO> listByDictKeyBox(String dictKey) {
        MmSysBasicDict baseBean = findByDictKey(null, dictKey);
        if(baseBean == null){
            return new ArrayList<>();
        }
        QueryWrapper<MmSysBasicDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysBasicDict.PARENT_ID, baseBean.getId());
        return getBasicDictVOS(queryWrapper);
    }

    public MmSysBasicDict findByDictKey(String dictKey) {

        return findByDictKey(null, dictKey, false);
    }

    private List<BasicDictVO> getBasicDictVOS(QueryWrapper<MmSysBasicDict> queryWrapper) {
        List<MmSysBasicDict> dictList = service.list(queryWrapper);
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


}
