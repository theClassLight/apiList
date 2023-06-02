package com.example.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.project.model.entity.InterfaceInfo;

/**
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
