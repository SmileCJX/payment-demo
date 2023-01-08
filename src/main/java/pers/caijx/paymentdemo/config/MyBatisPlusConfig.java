package pers.caijx.paymentdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MyBatisPlusConfig
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2023/1/1
 * @Version V1.0
 **/
@Configuration
@MapperScan("pers.caijx.paymentdemo.mapper")
@EnableTransactionManagement // 启用事务管理
public class MyBatisPlusConfig {
}
