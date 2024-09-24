package com.yj.reservation.pojo.cms.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuVO implements Comparable<MenuVO>{

    @ApiModelProperty("ID")
    private Long id;
    private int index;
    private String title;
    private List<MenuItemVO> mvo;


    @Override
    public int compareTo(MenuVO o) {
        return this.index - o.getIndex();
    }
}
