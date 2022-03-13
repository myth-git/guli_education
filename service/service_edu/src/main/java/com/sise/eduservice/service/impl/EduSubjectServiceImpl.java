package com.sise.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.sise.eduservice.entity.EduSubject;
import com.sise.eduservice.entity.excel.SubjectData;
import com.sise.eduservice.listener.SubjectExcelListener;
import com.sise.eduservice.mapper.EduSubjectMapper;
import com.sise.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author myth
 * @since 2022-03-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类,先读取excel表格，在进行保存
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //从文件输入流读取表格
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
