package com.xiaobao.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 启动类
 * @author lbw
 */
@SpringBootApplication
@MapperScan(basePackages = "com.xiaobao.gmall.user.mapper")
public class GmallUserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserManageApplication.class, args);
    }

}
