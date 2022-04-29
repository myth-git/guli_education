package com.sise.eduorder.service.impl;

import com.sise.eduorder.entity.Order;
import com.sise.eduorder.mapper.OrderMapper;
import com.sise.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author myth
 * @since 2022-04-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
