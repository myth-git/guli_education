package com.sise.staservice.service;

        import com.sise.staservice.entity.StatisticsDaily;
        import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author myth
 * @since 2022-04-30
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    //统计某一天注册人数,生成统计数据
    void registerCount(String day);
}
