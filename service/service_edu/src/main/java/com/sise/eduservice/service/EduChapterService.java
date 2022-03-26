package com.sise.eduservice.service;

import com.sise.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author myth
 * @since 2022-03-25
 */
public interface EduChapterService extends IService<EduChapter> {
    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
