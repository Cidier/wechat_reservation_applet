package ${voPath};

<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
</#if>

import java.util.Date;

/**
* <p>
    * ${table.comment!}
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Getter
@Setter
<#if chainModel>
@Accessors(chain = true)
</#if>
</#if>
<#if table.convert>
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "$!{table.comment}")
<#elseif swagger>
</#if>
<#if superEntityClass??>
public class ${entity}VO extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity}VO extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity}VO implements Serializable {
<#else>
public class ${entity}VO {
</#if>
<#if entitySerialVersionUID>
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#if field.keyFlag>
<#assign keyPropertyName="${field.propertyName}"/>
</#if>

<#if field.comment!?length gt 0>
<#if springdoc>
    @Schema(description = "${field.comment}")
<#elseif field.versionField || field.logicDeleteField>
<#elseif swagger>
    @ApiModelProperty("${field.comment}")
<#else>
    /**
    * ${field.comment}
    */
</#if>
</#if>
<#if field.keyFlag>
<#elseif field.fill??>
</#if>
<#if  field.versionField || field.logicDeleteField>
<#else>
    <#if field.propertyType == "BigDecimal">
    private Double ${field.propertyName};
    <#else>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#if>
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
<#list table.fields as field>
    <#if field.propertyType == "boolean">
        <#assign getprefix="is"/>
    <#else>
        <#assign getprefix="get"/>
    </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

    <#if chainModel>
        public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
        public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
    this.${field.propertyName} = ${field.propertyName};
    <#if chainModel>
        return this;
    </#if>
    }
</#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>

    public static final String ${field.name?upper_case} = "${field.name}";
    </#list>
</#if>
<#if activeRecord>

    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }
</#if>
<#if !entityLombokModel>

    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName} = " + ${field.propertyName} +
        <#else>
            ", ${field.propertyName} = " + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}

