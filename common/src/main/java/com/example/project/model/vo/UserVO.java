package com.example.project.model.vo;
/*
 *Administrator
 *2023/4/12 18:05
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.project.model.entity.User;
import lombok.Data;
@Data
public class UserVO {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String useraccount;


    /**
     * 微信开放平台id
     */
    private String unionid;

    /**
     * 公众号openId
     */
    private String mpopenid;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String useravatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户简介
     */
    private String userprofile;

    /**
     * 标签 json 列表
     */
    private String tags;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUseraccount() == null ? other.getUserAccount() == null : this.getUseraccount().equals(other.getUserAccount()))
                && (this.getUnionid() == null ? other.getUnionId() == null : this.getUnionid().equals(other.getUnionId()))
                && (this.getMpopenid() == null ? other.getMpOpenId() == null : this.getMpopenid().equals(other.getMpOpenId()))
                && (this.getUsername() == null ? other.getUserName() == null : this.getUsername().equals(other.getUserName()))
                && (this.getUseravatar() == null ? other.getUserAvatar() == null : this.getUseravatar().equals(other.getUserAvatar()))
                && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
                && (this.getUserprofile() == null ? other.getUserProfile() == null : this.getUserprofile().equals(other.getUserProfile()))
                && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUseraccount() == null) ? 0 : getUseraccount().hashCode());
        result = prime * result + ((getUnionid() == null) ? 0 : getUnionid().hashCode());
        result = prime * result + ((getMpopenid() == null) ? 0 : getMpopenid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUseravatar() == null) ? 0 : getUseravatar().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getUserprofile() == null) ? 0 : getUserprofile().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", useraccount=").append(useraccount);
        sb.append(", unionid=").append(unionid);
        sb.append(", mpopenid=").append(mpopenid);
        sb.append(", username=").append(username);
        sb.append(", useravatar=").append(useravatar);
        sb.append(", gender=").append(gender);
        sb.append(", userprofile=").append(userprofile);
        sb.append(", tags=").append(tags);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
