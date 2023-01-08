package pers.caijx.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.caijx.paymentdemo.entity.RefundInfo;
import pers.caijx.paymentdemo.mapper.RefundInfoMapper;
import pers.caijx.paymentdemo.service.RefundInfoService;

@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements RefundInfoService {

}
