package pers.caijx.paymentdemo.service.impl;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Ordering;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import pers.caijx.paymentdemo.entity.OrderInfo;
import pers.caijx.paymentdemo.entity.Product;
import pers.caijx.paymentdemo.enums.OrderStatus;
import pers.caijx.paymentdemo.mapper.OrderInfoMapper;
import pers.caijx.paymentdemo.mapper.ProductMapper;
import pers.caijx.paymentdemo.service.OrderInfoService;
import pers.caijx.paymentdemo.util.OrderNoUtils;

import javax.annotation.Resource;

@Service
public class OrderInfoServiceImpl
        extends ServiceImpl<OrderInfoMapper, OrderInfo>
        implements OrderInfoService {

    @Resource
    private ProductMapper productMapper;

//    用baseMapper会更简洁些
//    @Resource
//    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfo createOrderByProductId(Long productId) {
        // 查找已存在但未支付的订单
        OrderInfo orderInfo = this.getNoPayOrderByProductId(productId);

        if (null != orderInfo) {
            return orderInfo;
        }

        // 获取商品信息
        Product product = productMapper.selectById(productId);

        // 生成订单
        orderInfo = new OrderInfo();
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo()); // 订单号
        orderInfo.setProductId(productId);
        orderInfo.setTotalFee(product.getPrice()); // 分
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        baseMapper.insert(orderInfo);

        return orderInfo;
    }

    /**
     * 存储订单二维码（因为code_url的有效期是两个小时，可以保存下来）
     * @param orderNo
     * @param codeUrl
     */
    @Override
    public void saveCodeUrl(String orderNo, String codeUrl) {
        QueryWrapper<OrderInfo> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCodeUrl(codeUrl);
        baseMapper.update(orderInfo, queryWrapper);
    }

    /**
     * 查询订单列表，并倒序查询
     * @return
     */
    @Override
    public List<OrderInfo> listOrderByCreateTimeDesc() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>()
                .orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据商品id查询未支付订单
     * 防止重复创建订单对象
     * @param productId
     * @return
     */
    private OrderInfo getNoPayOrderByProductId(Long productId) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
//        queryWrapper.eq("user_id", userId)
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        return orderInfo;
    }

}
