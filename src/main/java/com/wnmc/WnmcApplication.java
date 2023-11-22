package com.wnmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author huming
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class WnmcApplication
{
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WnmcApplication.class, args);
        System.out.println("--------------系统启动成功-------------------\n"+"学号：20116040030\n"+"姓名：杨辉\n");
    }
}