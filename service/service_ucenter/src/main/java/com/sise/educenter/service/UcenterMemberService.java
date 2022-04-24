package com.sise.educenter.service;

import com.sise.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
