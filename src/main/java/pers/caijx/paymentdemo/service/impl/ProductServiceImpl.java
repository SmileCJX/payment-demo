package pers.caijx.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.caijx.paymentdemo.entity.Product;
import pers.caijx.paymentdemo.mapper.ProductMapper;
import pers.caijx.paymentdemo.service.ProductService;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
