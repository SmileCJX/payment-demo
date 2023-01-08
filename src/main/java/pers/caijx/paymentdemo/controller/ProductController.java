package pers.caijx.paymentdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pers.caijx.paymentdemo.entity.Product;
import pers.caijx.paymentdemo.service.ProductService;
import pers.caijx.paymentdemo.vo.R;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductController
 * @Description: TODO
 * @Author JunXiangCai
 * @Date 2022/12/31
 * @Version V1.0
 **/
@CrossOrigin // 开放前端的跨域访问
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;


    @ApiOperation("测试接口")
    @GetMapping("/test")
    public R test() {
        return R.ok()
                .data("message", "hello")
                .data("now", new Date());
    }

    @GetMapping("/list")
    public R list() {
        List<Product> list = productService.list();
        return R.ok().data("productList", list);
    }

}
