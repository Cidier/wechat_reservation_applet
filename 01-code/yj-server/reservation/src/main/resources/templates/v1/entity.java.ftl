package ${package.Entity};


<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.baomidou.mybatisplus.annotation.TableField;
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
 * <p>
    * ${table.comment!}
    * </p>
 *
 * @author ${author}
 */
<#if entityLombokModel>
@Data
    <#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if springdoc>
@Schema(name = "${entity}", description = "$!{table.comment}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity} implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

<#--<#if field.comment!?length gt 0>-->
<#--<#if swagger2>-->
<#--@ApiModelProperty(value = "${field.comment}")-->
<#--<#else>-->
<#--/**-->
<#--* ${field.comment}-->
<#--*/-->
<#--</#if>-->
<#--</#if>-->
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
        <#elseif field.keyFlag>
    @TableId("${field.name}")
        <#elseif idType??>
    @TableId(value = "${field.name}", type = IdType.${idType})
        <#elseif field.convert>
    @TableId("${field.name}")
        </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
        <#else>
    @TableField(fill = FieldFill.${field.fill})
        </#if>
<#--    <#elseif field.convert>-->
<#--    @TableField("${field.name}")-->
    </#if>
<#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
    @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
    </#if>
    <#if !field.convert && field.name?starts_with('is_') && field.columnType == "BOOLEAN">
    @TableField("${field.name}")
    </#if>
    <#if  field.propertyName == "mTime">
    @TableField(update = "NOW()")
    </#if>

    <#if field.propertyType == "BigDecimal">
    private Double ${field.propertyName};
    <#else>
    private ${field.propertyType} ${field.propertyName};
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

        <#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }

    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
    public static final String TABLE_NAME = "${table.name}";

    <#list table.fields as field>
    public static final String ${field.name?upper_case}_OF_TABLE = "${table.name}.${field.name}";

    </#list>
    public static final String[] ALL_COLUMNS = new String[]{
    <#list table.fields as field>
        "${table.name}.${field.name}"<#if field_index!=table.fields?size-1>,</#if>
    </#list>};
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
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
        "${field.propertyName}=" + ${field.propertyName} +
        <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>

<#if !entityLombokModel>
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
    ${entity} object = (${entity}) o;
    <#list table.fields as field>
        <#if field_index==0>
        return Objects.equals(${field.propertyName}, object.${field.propertyName}) &&
        <#elseif field_index==table.fields?size-1>
            Objects.equals(${field.propertyName}, object.${field.propertyName});
        <#else>
            Objects.equals(${field.propertyName}, object.${field.propertyName}) &&
        </#if>
    </#list>
    }
</#if>

<#if !entityLombokModel>
    @Override
    public int hashCode() {
        return Objects.hash(<#list table.fields as field><#if field_index!= 0 && field_index%7==0>
</#if>${field.propertyName}<#if field_index!=table.fields?size-1>, </#if></#list>);
    }
</#if>
}
