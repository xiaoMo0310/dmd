package com.dmd.mall;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * mybatisPlus代码生成器
 * @Author: YangAnsheng
 * @Date: 2019/9/22 12:29
 */

public class CodeGeneration {

    public static void main(String[] args) {
        mybatisPlusCodeGeneratro(new String[]{"ums_member"}, "dmd-admin");
    }

    /**
     * @Title: main
     * @Description: 生成
     * @param tables
     */
    public static void mybatisPlusCodeGeneratro(String[] tables, String file) {
        //用来获取mybatis-plus.properties文件的配置信息
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(file + "\\src\\main\\java");//输出文件路径
        gc.setFileOverride(false);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存s
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(false);
        gc.setAuthor("YangAnsheng");// 作者
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://192.168.0.114:3306/mall");

        //文件类型的转换
        /*dsc .setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig gc, String fieldType) {
                //System.out.println("转换类型：" + fieldType);
                // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                // return DbColumnType.BOOLEAN;
                // }
                return super.processTypeConvert(gc, fieldType);
            }
        });*/

        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(tables);
        strategy.setVersionFieldName("version");
        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(new String[]{"id","version","creator","creator_id","created_time","last_operator","last_operator_id","update_time"});
        // 自定义实体父类
        strategy.setSuperEntityClass("com.dmd.core.mybatis.BaseEntity");
        // // 自定义 mapper 父类
        strategy.setSuperMapperClass("com.dmd.core.mybatis.MyMapper");
        // // 自定义 service 父类
        strategy.setSuperServiceClass("com.dmd.core.support.IService");
        // // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.dmd.core.support.BaseService");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("com.dmd.core.support.BaseController");
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dmd.mall");
        pc.setController("web");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("model.domain");
        //pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("swagger2", true);
                this.setMap(map);
            }
        };
        // 调整 xml 生成目录演示
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                System.out.println(tableInfo);
                return "dmd-mall\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 logger-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("/templatesMybatis/entity.java.vm");
        tc.setController("/templatesMybatis/controller.java.vm");
        tc.setService("/templatesMybatis/service.java.vm");
        tc.setServiceImpl("/templatesMybatis/serviceImpl.java.vm");
        tc.setMapper("/templatesMybatis/mapper.java.vm");
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

    }
}
