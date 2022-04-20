package com.sise.educms.service.impl;

import com.sise.educms.entity.CrmBanner;
import com.sise.educms.mapper.CrmBannerMapper;
import com.sise.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author myth
 * @since 2022-04-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    //查询所有banner
    @Override
    public List<CrmBanner> selectAllBanner() {
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
