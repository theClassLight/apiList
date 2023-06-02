package com.example.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.project.common.BaseResponse;
import com.example.project.common.ResultUtils;
import com.example.project.domain.dto.post.PostQueryRequest;
import com.example.project.domain.dto.search.SearchRequest;
import com.example.project.domain.entity.Post;
import com.example.project.domain.vo.SearchVO;
import com.example.project.manage.SearchFacade;
import com.example.project.service.PictureService;
import com.example.project.service.PostService;
import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @DubboReference
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }
    @PostMapping("/mysql")
    public BaseResponse<Page<Post>> searchMysql(@RequestBody SearchRequest searchRequest, HttpServletRequest request){

        PostQueryRequest queryRequest = new PostQueryRequest();
        queryRequest.setSearchText(searchRequest.getSearchText());
        return ResultUtils.success(postService.searchFromMysql(queryRequest));
    }
}
