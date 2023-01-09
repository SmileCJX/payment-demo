package pers.caijx.paymentdemo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pers.caijx.paymentdemo.service.WxPayService;
import pers.caijx.paymentdemo.util.HttpUtils;
import pers.caijx.paymentdemo.util.WechatPay2ValidatorForRequest;
import pers.caijx.paymentdemo.vo.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private Verifier verifier;

    @ApiOperation("生成统一下单支付api，生成支付二维码")
    @PostMapping("native/{productId}")
    public R nativePay(@PathVariable Long productId) throws Exception {
      log.info("发起支付请求");
      // 返回支付二维码链接和订单号
      Map<String,Object> map = wxPayService.nativePay(productId);

      return R.ok().setData(map);
    }

    @PostMapping("/native/notify")
    public String nativeNotify(HttpServletRequest request, HttpServletResponse response) {

        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>(); // 应当对象
        try {

//            对后台通知交互时，如果微信收到商户的应答不符合规范或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。（通知频率为15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m）
//            int i = 1 / 0;
//            TimeUnit.SECONDS.sleep(5);
            // 处理通知参数
            String body = HttpUtils.readData(request);
            Map<String, Object> bodyMap = gson.fromJson(body, HashMap.class);
            log.info("支付通知的id ===》 {}", bodyMap.get("id"));
            String requestId = (String) bodyMap.get("id");
            log.info("支付通知的完整数 ===》 {}", body);
            // TODO: 签名的验证
            WechatPay2ValidatorForRequest wechatPay2ValidatorForRequest = new WechatPay2ValidatorForRequest(verifier
                    , body
                    , requestId);
            if (!wechatPay2ValidatorForRequest.validate(request)) {
                log.error("通知验签失败");
                // 失败应答
                response.setStatus(500);
                map.put("code", "ERROR");
                map.put("message", "通知验签失败");
                return gson.toJson(map);
            }
            log.info("通知验签成功");
            // TODO： 处理订单

            // 成功应答
            response.setStatus(200);
            map.put("code", "SUCCESS");
            map.put("message", "成功");
            return gson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            // 失败应答
            response.setStatus(500);
            map.put("code", "ERROR");
            map.put("message", "失败");
            return gson.toJson(map);
        }
    }
}
