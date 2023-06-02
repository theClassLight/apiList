package com.example.project.model.request.UserRequest;
/*
 *Administrator
 *2023/4/11 21:21
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

 private static final long serialVersionUID = 3191241716373120793L;

 private String userAccount;

 private String userPassword;
}
