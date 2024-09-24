package com.yj.reservation.common.generator;

import lombok.Data;

import java.io.File;

@Data
public class GeneratorConfig {

    String ip = "103.81.48.25";
    String port = "13307";
    String database = "mm_zhongke";
    String username = "root";
    String password = "HdxinxingDev.8";


    String moduleName = "mb";//模块名称

    String packagePath = "com.mm.zhongke";
    String mapperPath = "mapper."+moduleName;
    String entityPath = "entity."+moduleName;
    String servicePath = "service."+moduleName;
    String serviceImplPath = servicePath+".impl";
    String controllerPath = "controller."+moduleName;

    String entity = "templates/v1/entity.java";
    String mapper = "templates/v1/mapper.java";
    String service = "templates/v1/service.java";
    String serviceImpl = "templates/v1/serviceImpl.java";
    String controller = "templates/v1/controller.java";


    String voPath = packagePath + ".pojo."+moduleName+".vo";
    String dtoPath = packagePath + ".pojo."+moduleName+".dto";
    String paramQueryPath = packagePath + ".pojo."+moduleName+".query";


    String vo = "templates/v1/vo.java.ftl";
    String dto = "templates/v1/dto.java.ftl";
    String paramQuery = "templates/v1/paramQuery.java.ftl";

    String superMapperClass = "com.mm.zhongke.common.mapper.ExtensionBaseMapper";

    File f = new File(MybatisGeneratorV1App.class.getResource("/").getPath());
    String projectPath = f.getParentFile().getParent() + "/src/main/java";
}