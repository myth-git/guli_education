package com.sise.staservice.controller;


import com.sise.commonutils.R;
import com.sise.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author myth
 * @since 2022-04-30
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return R.ok();
    }
}

