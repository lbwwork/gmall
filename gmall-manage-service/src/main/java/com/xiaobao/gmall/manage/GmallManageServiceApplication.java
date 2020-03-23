package com.xiaobao.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lbw
 */
@SpringBootApplication
@MapperScan("com.xiaobao.gmall.manage.mapper")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.xiaobao.gmall")
public class GmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallManageServiceApplication.class, args);
    }

}
