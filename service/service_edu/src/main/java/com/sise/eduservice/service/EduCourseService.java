package com.sise.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.eduservice.entity.frontvo.CourseFrontVo;
import com.sise.eduservice.entity.frontvo.CourseWebVo;
import com.sise.eduservice.entity.vo.CourseInfoVo;
import com.sise.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

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
    //根据课程id查出课程信息
    CourseInfoVo getCourseInfo(String courseId);
    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);
    //删除课程
    void removeCourse(String courseId);
    //1 条件查询带分页查询课程
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
    //2 课程详情的方法
    CourseWebVo getBaseCourseInfo(String courseId);
}
