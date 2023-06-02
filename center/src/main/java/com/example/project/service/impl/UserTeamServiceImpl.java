package com.example.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.project.mapper.UserTeamMapper;
import com.example.project.model.entity.UserTeam;
import com.example.project.service.UserTeamService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
@DubboService
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {

}




