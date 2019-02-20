package com.wjc.util;

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
 * <p>
 * 生成代码工具类
 * </p>
 * @author wangjc
 * @date 2017/12/28
 */
public class MybatisPlusUtils {
    public static String subPackage = ".sys";

    public static void main(String[] args) {
        String packageName = "com.wjc";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        String[] tables = new String[]{"busi_invoice_manager",
                "busi_logistics_company","busi_logistics_company_attach", "busi_operate_company", "busi_service_company"};
        generateByTables(serviceNameStartWithI, packageName, "sys_user_ext");
    }
    //public void generateCode() {
    //    String packageName = "com.baomidou.springboot";
    //    boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
    //    generateByTables(serviceNameStartWithI, packageName, "user", "role");
    //}

    public static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setAuthor("wangjc")
                //.setOutputDir("d:\\codeGen")
                .setFileOverride(true);
        config.setBaseColumnList(true);
        config.setBaseResultMap(true);
        config.setFileOverride(true);

        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/src/main/java");

        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);
        packageConfig.setController("controller"+subPackage);
        packageConfig.setEntity("entity"+subPackage);
        packageConfig.setMapper("dao"+subPackage);
        packageConfig.setService("service" +subPackage);
        packageConfig.setServiceImpl("service"+subPackage+".impl");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出xml映射文件配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/"+ subPackage.substring(1)+"/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig templateConfig = new TemplateConfig();
        //templateConfig.setEntity();
        templateConfig.setXml(null);

        String dbUrl = "jdbc:mysql://localhost:3306/auth-dev";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("1")
                .setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("busi")//sys_sms->sms
                .setRestControllerStyle(true)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(cfg)
                .setTemplate(templateConfig)
                .execute();
    }
}