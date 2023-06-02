package com.example.project;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.project.excel.DataListener;
import com.example.project.model.entity.User;
import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.File;

/*
 *Administrator
 *2023/5/10 20:14
 */
@SpringBootTest
@Slf4j
public class excelTest {

    @Resource
    UserService userService;

    @Test
    public void Write() {

        Page<User> userPage;
        // 无缓存，查数据库
        Long pageSize = 5L;
        Long pageNum = 0L;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (pageSize == null) {
            pageSize = 5L;
        }
        if (pageNum == null) {
            pageNum = 0L;
        }

        userPage = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        // 方法1: 如果写到同一个sheet
        String fileName = "D:\\test\\testExcel.xlsx";
        // 这里 需要指定写用哪个class去写
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 500 * 200
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 1; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据

                excelWriter.write(userPage.getRecords(), writeSheet);
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String fileName = "D:\\test\\testExcel.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, User.class, new DataListener(userService)
        ).sheet().doRead();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
