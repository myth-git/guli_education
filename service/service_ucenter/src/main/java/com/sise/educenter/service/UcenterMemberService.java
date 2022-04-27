package com.sise.educenter.service;

import com.sise.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author myth
 * @since 2022-04-24
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //手机登录
    String login(UcenterMember member);
    //手机注册
    void register(RegisterVo registerVo);
    //判断是否有重复openid
    UcenterMember getOpenIdMember(String openid);
}
