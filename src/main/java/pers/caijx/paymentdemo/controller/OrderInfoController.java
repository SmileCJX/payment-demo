package pers.caijx.paymentdemo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.caijx.paymentdemo.entity.OrderInfo;
import pers.caijx.paymentdemo.service.OrderInfoService;
import pers.caijx.paymentdemo.vo.R;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName OrderInfoController
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2023/1/8
 * @Version V1.0
 **/
@CrossOrigin // 开放前端的跨域访问
@Api(tags = "商品订单管理")
@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/list")
    public R list() {
        List<OrderInfo> list = orderInfoService.listOrderByCreateTimeDesc();
        return R.ok().data("list", list);
    }
}
