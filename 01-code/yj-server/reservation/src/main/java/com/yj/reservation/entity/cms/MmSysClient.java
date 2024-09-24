package com.yj.reservation.entity.cms;



import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 客户端表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_client")
@ApiModel(value = "MmSysClient对象", description = "客户端表")
public class MmSysClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")

    private Long id;


    private String clientId;


    private String clientSecret;


    private String resourceIds;


    private String scope;


    private String authorizedGrantTypes;


    private String webServerRedirectUri;


    private String authorities;


    private Integer accessTokenValidity;


    private Integer refreshTokenValidity;


    private String additionalInformation;


    private String autoapprove;


    private Long createDept;


    private Date cTime;


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

    public static final String TABLE_NAME = "mm_sys_client";

    public static final String ID_OF_TABLE = "mm_sys_client.id";

    public static final String CLIENT_ID_OF_TABLE = "mm_sys_client.client_id";

    public static final String CLIENT_SECRET_OF_TABLE = "mm_sys_client.client_secret";

    public static final String RESOURCE_IDS_OF_TABLE = "mm_sys_client.resource_ids";

    public static final String SCOPE_OF_TABLE = "mm_sys_client.scope";

    public static final String AUTHORIZED_GRANT_TYPES_OF_TABLE = "mm_sys_client.authorized_grant_types";

    public static final String WEB_SERVER_REDIRECT_URI_OF_TABLE = "mm_sys_client.web_server_redirect_uri";

    public static final String AUTHORITIES_OF_TABLE = "mm_sys_client.authorities";

    public static final String ACCESS_TOKEN_VALIDITY_OF_TABLE = "mm_sys_client.access_token_validity";

    public static final String REFRESH_TOKEN_VALIDITY_OF_TABLE = "mm_sys_client.refresh_token_validity";

    public static final String ADDITIONAL_INFORMATION_OF_TABLE = "mm_sys_client.additional_information";

    public static final String AUTOAPPROVE_OF_TABLE = "mm_sys_client.autoapprove";

    public static final String CREATE_DEPT_OF_TABLE = "mm_sys_client.create_dept";

    public static final String C_TIME_OF_TABLE = "mm_sys_client.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_client.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_client.id",
            "mm_sys_client.client_id",
            "mm_sys_client.client_secret",
            "mm_sys_client.resource_ids",
            "mm_sys_client.scope",
            "mm_sys_client.authorized_grant_types",
            "mm_sys_client.web_server_redirect_uri",
            "mm_sys_client.authorities",
            "mm_sys_client.access_token_validity",
            "mm_sys_client.refresh_token_validity",
            "mm_sys_client.additional_information",
            "mm_sys_client.autoapprove",
            "mm_sys_client.create_dept",
            "mm_sys_client.c_time",
            "mm_sys_client.m_time"
    };


}
