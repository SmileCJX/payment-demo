package pers.caijx.paymentdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pers.caijx.paymentdemo.service.WxPayService;
import pers.caijx.paymentdemo.vo.R;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName WxPayController
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2023/1/1
 * @Version V1.0
 **/
@CrossOrigin // 跨域
@RestController
@RequestMapping("/api/wx-pay")
@Api(tags = "网站微信支付API")
@Slf4j
public class WxPayController {

    @Resource
    private WxPayService wxPayService;

    @ApiOperation("生成统一下单支付api，生成支付二维码")
    @PostMapping("native/{productId}")
    public R nativePay(@PathVariable Long productId) throws Exception {
      log.info("发起支付请求");
      // 返回支付二维码链接和订单号
      Map<String,Object> map = wxPayService.nativePay(productId);

      return R.ok().setData(map);
    }
}
