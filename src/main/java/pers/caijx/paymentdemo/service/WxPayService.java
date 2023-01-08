package pers.caijx.paymentdemo.service;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName WxPayService
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2023/1/1
 * @Version V1.0
 **/
public interface WxPayService {

    Map<String, Object> nativePay(Long productId) throws Exception;
}
