package com.sise.eduorder.service;

import com.sise.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author myth
 * @since 2022-04-29
 */
public interface PayLogService extends IService<PayLog> {
    //生成微信支付二维码接口
    Map createNatvie(String orderNo);
    //查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);
    //添加支付记录和更新订单状态
    void updateOrdersStatus(Map<String, String> map);
}
