package pers.caijx.paymentdemo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.paymentdemo.config.WxPayConfig;
import pers.caijx.paymentdemo.vo.R;

import javax.annotation.Resource;

/**
 * @ClassName TestController
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2023/1/1
 * @Version V1.0
 **/
@Api(tags = "测试控制器")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Resource
    private WxPayConfig wxPayConfig;

    @GetMapping
    public R getWxPayConfig() {
        String mchId = wxPayConfig.getMchId();
        return R.ok().data("mchId", mchId);
    }
}
