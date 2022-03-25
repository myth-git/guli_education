package com.sise.eduservice.service;

import com.sise.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author myth
 * @since 2022-03-12
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类,先读取excel表格，在进行保存
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
