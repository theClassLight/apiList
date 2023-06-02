package com.example.project.model.request.UserRequest;

import lombok.Data;

/*
 *Administrator
 *2023/4/10 20:32
 */
@Data
public class UserRegisterRequest {
    private static final long serialVersionUID = 3191241716374470793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
