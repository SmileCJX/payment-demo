package pers.caijx.paymentdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.caijx.paymentdemo.config.WxPayConfig;

import javax.annotation.Resource;
import java.security.PrivateKey;

@SpringBootTest
class PaymentDemoApplicationTests {

    @Resource
    private WxPayConfig wxPayConfig;

//    /**
//     * 获取商户私钥
//     */
//    @Test
//    void testGetPrivateKey() {
//        // 获取私钥路径
//        String privateKeyPath = wxPayConfig.getPrivateKeyPath();
//        // 获取私钥
//        PrivateKey privateKey = wxPayConfig.getPrivateKey(privateKeyPath);
//        System.out.println(privateKey);
//    }

}
