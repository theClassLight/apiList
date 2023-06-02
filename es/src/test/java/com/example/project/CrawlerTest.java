package com.example.project;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.project.domain.entity.Picture;
import com.example.project.domain.entity.Post;
import com.example.project.service.PostService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CrawlerTest {

    @Resource
    private PostService postService;

    @Test
    void testFetchPicture() throws IOException {
        int current = 1;
        String url = "https://cn.bing.com/images/search?q=妹子&first=" + current;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.varh.isv");
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            // 取图片地址（murl）
            String m = element.select(".mimg").get(0).attr("src");
//            System.out.println(murl);
            // 取标题
            String title = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(title, Map.class);
            String murl = (String) map.get("t");
            title = murl;
//            System.out.println(title);
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(m);
            pictures.add(picture);
        }
        System.out.println(pictures);
    }

    @Test
    void testFetchPassage() {
        // 1. 获取数据
        String json = "{\"current\":1,\"pageSize\":8,\"sortField\":\"createTime\",\"sortOrder\":\"descend\",\"category\":\"文章\",\"reviewStatus\":1}";
        String url = "https://www.code-nav.cn/api/post/search/page/vo";
        String result = HttpRequest
                .post(url)
                .body(json)
                .execute()
                .body();
//        System.out.println(result);
        // 2. json 转对象
        Map<String, Object> map = JSONUtil.toBean(result, Map.class);
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records = (JSONArray) data.get("records");
        List<Post> postList = new ArrayList<>();
        for (Object record : records) {
            JSONObject tempRecord = (JSONObject) record;
            Post post = new Post();
            post.setTitle(tempRecord.getStr("title"));
            post.setContent(tempRecord.getStr("content"));
            JSONArray tags = (JSONArray) tempRecord.get("tags");
            List<String> tagList = tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(tagList));
            post.setUserId(1L);
            postList.add(post);
        }
//        System.out.println(postList);
        // 3. 数据入库
        boolean b = postService.saveBatch(postList);
        Assertions.assertTrue(b);
    }
}
