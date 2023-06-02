package com.example.project;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.project.excel.createData;
import com.example.project.model.entity.User;
import com.example.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/*
 *Administrator
 *2023/5/10 20:14
 */
@SpringBootTest
public class excelTest {

    @Resource
    UserService userService;

    @Test
    public void test(){

        Page<User> userPage;
        // 无缓存，查数据库
        Long pageSize = 5L;
        Long pageNum = 0L;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (pageSize == null){
            pageSize = 5L;
        }if (pageNum == null){
            pageNum = 0L;
        }

        userPage = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        // 方法1: 如果写到同一个sheet
        String fileName = "D:\\test\\testExcel.xlsx";
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, createData.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据

                excelWriter.write(userPage.getRecords(), writeSheet);
            }
        }
    }
}
