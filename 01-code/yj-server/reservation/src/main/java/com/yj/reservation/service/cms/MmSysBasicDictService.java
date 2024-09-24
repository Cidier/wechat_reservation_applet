package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysBasicDict;
import com.yj.reservation.pojo.cms.query.MmSysBasicDictQuery;
import com.yj.reservation.pojo.cms.dto.MmSysBasicDictDTO;
import com.yj.reservation.pojo.cms.vo.BasicDictVO;
import com.yj.reservation.pojo.cms.vo.MmSysBasicDictVO;

import java.util.List;


/**
* <p>
    * 字典表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysBasicDictService extends IService<MmSysBasicDict> {

    /**
    * 新增 字典表
    *
    * @param dto 参数
    */
    void addMmSysBasicDict(MmSysBasicDictDTO dto);

    /**
    * 修改 字典表
    *
    * @param dto 参数
    */
    void modifyMmSysBasicDict(MmSysBasicDictDTO dto);

    /**
    * 删除 字典表
    *
    * @param id 主键
    */
    void deleteMmSysBasicDict(Long id);

    /**
    * 根据id获取 字典表 详情
    *
    * @param id 主键
    */
    MmSysBasicDictVO queryMmSysBasicDictById(Long id);


    /**
    * 分页查询 字典表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysBasicDictVO> pageList(MmSysBasicDictQuery query);

    List<BasicDictVO> list(Long parentId);

    List<BasicDictVO> listByDictKeyBox(String dictKey);

    MmSysBasicDict findByDictKey(String dictKey);
}
