package com.example.project.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/*
 *Administrator
 *2023/4/26 9:57
 */
@Data
@EqualsAndHashCode
public class createData {
    /**
     * id
     */
    @ExcelProperty("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String useraccount;

    /**
     * 密码
     */
    @ExcelProperty("密码")
    private String userpassword;

    /**
     * 微信开放平台id
     */
    @ExcelProperty("微信开放平台id")
    private String unionid;

    /**
     * 公众号openId
     */
    @ExcelProperty("公众号openId")
    private String mpopenid;

    /**
     * 用户昵称
     */
    @ExcelProperty("用户昵称")
    private String username;

    /**
     * 用户头像
     */
    @ExcelProperty("用户头像")
    private String useravatar;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    private Integer gender;

    /**
     * 用户简介
     */
    @ExcelProperty("用户简介")
    private String userprofile;

    /**
     * 标签 json 列表
     */
    @ExcelProperty("标签 json 列表")
    private String tags;

    /**
     * 状态 0 - 正常
     */
    @ExcelProperty("状态 0 - 正常")
    private Integer userstatus;

    /**
     * accessKey
     */
    @ExcelProperty("accessKey")
    private String accesskey;

    /**
     * secretKey
     */
    @ExcelProperty("secretKey")
    private String secretkey;

    /**
     * 用户角色：user/admin/ban
     */
    @ExcelProperty("用户角色：user/admin/ban")
    private String userrole;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private Date createtime;

    /**
     * 更新时间
     */
    @ExcelProperty("更新时间")
    private Date updatetime;

    /**
     * 是否删除
     */
    @TableLogic
    @ExcelProperty("是否删除")
    private Integer isdelete;
}
