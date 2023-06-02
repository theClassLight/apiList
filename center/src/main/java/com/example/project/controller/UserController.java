package com.example.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.project.common.BaseResponse;
import com.example.project.common.ErrorCode;
import com.example.project.common.ResultUtils;
import com.example.project.constant.UserConstant;
import com.example.project.exception.BusinessException;
import com.example.project.model.entity.User;
import com.example.project.model.request.UserRequest.*;
import com.example.project.model.vo.UserVO;
import com.example.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


/*
 *Administrator
 *2023/4/10 18:38
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    // region 登录相关

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // TODO ak sk注册
        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }
    /**
     * 用户登录对照组
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/loginTest")
    public BaseResponse<User> userLoginTest(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLoginTest(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    // endregion

    // region 增删改查 查询是否是管理员
    /**
     * 创建用户
     *
     * @param userAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        long result = userService.userAdd(userAddRequest);
        return ResultUtils.success(result);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新用户
     *
     * @param userUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        return ResultUtils.success(result);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<UserVO> getUserById(int id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<UserVO>> listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        List<User> userList = userService.list(queryWrapper);
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }

    /**
     * 分页获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<UserVO>> listUserByPage(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
            current = userQueryRequest.getCurrent();
            size = userQueryRequest.getPageSize();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
        Page<UserVO> userVOPage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<UserVO> userVOList = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }

    // endregion
    /**
     * 查询当前用户
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        // TODO aop权限管理
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }
}
