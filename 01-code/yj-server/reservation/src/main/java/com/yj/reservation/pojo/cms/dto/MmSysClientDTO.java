package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 客户端表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysClientDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("客户端id")
    private String clientId;

    @ApiModelProperty("客户端密钥")
    private String clientSecret;

    @ApiModelProperty("资源集合")
    private String resourceIds;

    @ApiModelProperty("授权范围")
    private String scope;

    @ApiModelProperty("授权类型")
    private String authorizedGrantTypes;

    @ApiModelProperty("回调地址")
    private String webServerRedirectUri;

    @ApiModelProperty("权限")
    private String authorities;

    @ApiModelProperty("令牌过期秒数")
    private Integer accessTokenValidity;

    @ApiModelProperty("刷新令牌过期秒数")
    private Integer refreshTokenValidity;

    @ApiModelProperty("附加说明")
    private String additionalInformation;

    @ApiModelProperty("自动授权")
    private String autoapprove;

    @ApiModelProperty("创建部门")
    private Long createDept;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
    private Date mTime;

    public static final String ID = "id";

    public static final String CLIENT_ID = "client_id";

    public static final String CLIENT_SECRET = "client_secret";

    public static final String RESOURCE_IDS = "resource_ids";

    public static final String SCOPE = "scope";

    public static final String AUTHORIZED_GRANT_TYPES = "authorized_grant_types";

    public static final String WEB_SERVER_REDIRECT_URI = "web_server_redirect_uri";

    public static final String AUTHORITIES = "authorities";

    public static final String ACCESS_TOKEN_VALIDITY = "access_token_validity";

    public static final String REFRESH_TOKEN_VALIDITY = "refresh_token_validity";

    public static final String ADDITIONAL_INFORMATION = "additional_information";

    public static final String AUTOAPPROVE = "autoapprove";

    public static final String CREATE_DEPT = "create_dept";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

