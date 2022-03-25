package com.sise.eduservice.service;

import com.sise.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author myth
 * @since 2022-03-25
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
