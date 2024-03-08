package com.example.project.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.project.model.entity.User;
import com.example.project.model.request.MyHttpRequest;
import com.example.project.model.request.UserAddRequest;
import com.example.project.model.request.UserQueryRequest;
import com.example.project.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 添加用户
     * @param userAddRequest
     * @return
     */

    long userAdd(UserAddRequest userAddRequest);


    /**
     * 根据标签搜索用户
     *
     * @param tagNameList
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);

    long userAdd(com.example.project.model.request.UserRequest.UserAddRequest userAddRequest);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user, User loginUser);


    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
    boolean isAdmin(User loginUser);

    /**
     * 匹配用户
     * @param num
     * @param loginUser
     * @return
     */
    List<User> matchUsers(long num, User loginUser);

    /**
     * 获取查询条件 es配件 分页查询前置
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 分页查询用户 es配件
     * @param userQueryRequest
     */
    Page<UserVO> listUserVOByPage(UserQueryRequest userQueryRequest);
    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);

    /**
     * 序列化测试
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    User userLoginTest(String userAccount, String userPassword, HttpServletRequest request);

    //endregion
    // region 查询内容
    QueryWrapper<User> getQueryWrapper(com.example.project.model.request.UserRequest.UserQueryRequest userQueryRequest);

    Page<UserVO> listUserVOByPage(com.example.project.model.request.UserRequest.UserQueryRequest userQueryRequest);
}
