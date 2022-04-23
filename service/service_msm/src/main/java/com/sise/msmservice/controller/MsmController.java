package com.sise.msmservice.controller;

import com.sise.commonutils.R;
import com.sise.msmservice.service.MsmService;
import com.sise.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成四位随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
    //发送邮箱验证码
    @GetMapping("sendEmail/{email}")
    public R sendEmailCode(@PathVariable String email) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = (String) redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //如果从redis获取不到，
        // 生成四位验证码
        code = RandomUtil.getFourBitRandom();
        msmService.sendEmail(email,code);
        redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
        return R.ok();

    }
}
