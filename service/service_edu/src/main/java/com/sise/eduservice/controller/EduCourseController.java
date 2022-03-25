package com.sise.eduservice.controller;


import com.sise.commonutils.R;
import com.sise.eduservice.entity.vo.CourseInfoVo;
import com.sise.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author myth
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin//解决跨域问题
public class EduCourseController {

    @Autowired
    EduCourseService courseService;
    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
}

