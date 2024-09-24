package com.yj.reservation.common.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import jakarta.validation.constraints.NotNull;

import java.io.File;
import java.util.Map;

public class MybatisGeneratorV1App {



    public static void main(String[] args) {
        GeneratorConfig config = new GeneratorConfig();

        exceBasic(config);


    }


    public static void exceBasic(GeneratorConfig config){
        FastAutoGenerator
                .create("jdbc:mysql://"+config.ip+":"+config.port+"/"+config.database+"?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf-8"
                        , config.username, config.password)
                .globalConfig(builder ->
                        builder
                                .outputDir(config.projectPath) //输出到哪个目录
                                .author("yang")
                                .enableSwagger()
                                .dateType(DateType.ONLY_DATE))
                .packageConfig(builder ->
                                builder
                                        .parent(config.packagePath) // 父包名
                                        //  .moduleName("sys")   // 父包模块名
                                        .entity(config.entityPath)
                                        .service(config.servicePath)
                                        .serviceImpl(config.serviceImplPath)
                                        .mapper(config.mapperPath)
//                                .xml("mapper.xml")
                                        .controller(config.controllerPath)
                )
                .templateConfig(builder ->
                        builder
                                .disable(TemplateType.XML) //禁用模板
                                .entity(config.entity)
                                .service(config.service)
                                .serviceImpl(config.serviceImpl)
                                .mapper(config.mapper)
                                .controller(config.controller)
                )
                .injectionConfig(builder ->
                        builder
                                .beforeOutputFile((tableInfo, objectMap) -> {
                                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                                })

                                .customFile(new CustomFile.Builder().fileName("DTO.java").filePath(config.projectPath)
                                        .enableFileOverride().templatePath(config.dto).packageName(config.dtoPath).build())
                                .customFile(new CustomFile.Builder().fileName("VO.java").filePath(config.projectPath)
                                        .enableFileOverride().templatePath(config.vo).packageName(config.voPath).build())
                                .customFile(new CustomFile.Builder().fileName("Query.java").filePath(config.projectPath)
                                        .enableFileOverride().templatePath(config.paramQuery).packageName(config.paramQueryPath).build())

                )
                .strategyConfig(builder ->
                        builder
                                .mapperBuilder()
                                .superClass(config.superMapperClass) //设置 Mapper 父类
                                .enableMapperAnnotation())
                .strategyConfig(builder ->
                                builder
                                        .entityBuilder()
                                        .enableFileOverride()  // 覆盖已生成文件
                                        .enableChainModel()
                                        .enableLombok()
                                        .enableColumnConstant() //开启常量
                                        .enableTableFieldAnnotation()
                                        .addTableFills(new Column("c_time", FieldFill.INSERT))
                                        .addTableFills(new Property("m_time", FieldFill.INSERT_UPDATE))
                                        .idType(IdType.ASSIGN_ID)   // 主键的ID类型

                ).strategyConfig(builder ->
                        builder
                                .controllerBuilder()
                                .enableFileOverride()  //  覆盖已生成文件	默认值:false
                                .enableHyphenStyle()
                                .enableRestStyle()
                ).strategyConfig(builder ->
                        builder.
                                serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                )
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .strategyConfig(builder -> builder.addExclude())//
//                .templateEngine(new FreemarkerTemplateEngine())
                .templateEngine(new DiyFreemarkerTemplateEngine(config)) // 这里重写FreemarkerTemplateEngine
                .execute();
    }

    /**
     * 新增模版中的使用变量。
     *      .customFile(new CustomFile.Builder().fileName("Query.java").filePath(config.projectPath)
     *                                         .enableFileOverride().templatePath(config.paramQuery).packageName(config.paramQueryPath).build())
     *      该方式无法设置值到当前map中。
     */
    static class DiyFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
        GeneratorConfig config = null;
        public DiyFreemarkerTemplateEngine(GeneratorConfig config){
            this.config = config;
        }

        public void writer(@NotNull Map<String, Object> objectMap, @NotNull String templatePath, @NotNull File outputFile) throws Exception{
            System.out.println("------------------------------------------------------------");
            objectMap.keySet().forEach(key->{
                System.out.println(key+ ":" + objectMap.get(key));
            });

            objectMap.put("dtoPath", config.dtoPath);
            objectMap.put("voPath", config.voPath);
            objectMap.put("paramQueryPath", config.paramQueryPath);
            super.writer(objectMap, templatePath, outputFile);
        }
    }
}
