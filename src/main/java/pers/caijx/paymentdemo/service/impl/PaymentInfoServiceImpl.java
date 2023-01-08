package pers.caijx.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.caijx.paymentdemo.entity.PaymentInfo;
import pers.caijx.paymentdemo.mapper.PaymentInfoMapper;
import pers.caijx.paymentdemo.service.PaymentInfoService;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
