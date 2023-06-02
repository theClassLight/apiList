package com.example.project;
/*
 *Administrator
 *2023/5/16 16:27
 */

import com.example.project.domain.dto.post.PostEsDTO;
import com.example.project.esdao.PostEsDao;
import com.example.project.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class testSelect {

    @Resource
    private PostEsDao postEsDao;

    @Resource
    private PostService postService;
    @Test
    void testSelect() {
        System.out.println(postEsDao.count());
        Page<PostEsDTO> PostPage = postEsDao.findAll(
                PageRequest.of(0, 5, Sort.by("createTime")));
        List<PostEsDTO> postList = PostPage.getContent();
        Optional<PostEsDTO> byId = postEsDao.findById(1L);
        System.out.println(byId);
        System.out.println(postList);
    }

    @Test
    void testAdd() {
        PostEsDTO postEsDTO = new PostEsDTO();
        postEsDTO.setId(2L);
        postEsDTO.setTitle("鱼皮是小黑子");
        postEsDTO.setContent("鱼皮的知识星球：https://yupi.icu，直播带大家做项目");
        postEsDTO.setTags(Arrays.asList("java", "python"));
        postEsDTO.setUserId(1L);
        postEsDTO.setCreateTime(new Date());
        postEsDTO.setUpdateTime(new Date());
        postEsDTO.setIsDelete(0);
        postEsDao.save(postEsDTO);
        System.out.println(postEsDTO.getId());
    }
}
