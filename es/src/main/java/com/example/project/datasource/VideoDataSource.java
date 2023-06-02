package com.example.project.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.project.data_source.DataSource;
import org.springframework.stereotype.Component;

@Component
public class VideoDataSource implements DataSource<Object> {

    @Override
    public Page<Object> doSearch(String searchText, long pageNum, long pageSize) {
        return null;
    }
}
