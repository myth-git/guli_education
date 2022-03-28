package com.sise.eduservice.service;

import com.sise.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author myth
 * @since 2022-03-25
 */
public interface EduVideoService extends IService<EduVideo> {
    //删除小节
    void removeVideoByCourseId(String courseId);


}
