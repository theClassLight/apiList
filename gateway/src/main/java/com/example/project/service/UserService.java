package com.example.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.project.model.entity.User;
import com.example.project.model.request.UserRequest.UserAddRequest;

import javax.servlet.http.HttpServletRequest;


/**
* @author Administrator
* @description 针对表【user(用户)】的数据库操作Service 用户操作
* @createDate 2023-04-10 18:09:26
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     *
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     *
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public User getLoginUser(HttpServletRequest request);
    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);


    /**
     * tool 用户脱敏
     *
     * @param originUser
     * @return 脱敏后的用户信息
     */
    User getSafetyUser(User originUser);


    long userAdd(UserAddRequest userAddRequest);


    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

}
