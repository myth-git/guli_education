package com.sise.eduservice.controller;


import com.sise.eduservice.entity.EduTeacher;
import com.sise.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author myth
 * @since 2022-03-08
 */
@Api(description="讲师管理")//swagger中文友好提示
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")//采用路径传递 @PathVariable
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

