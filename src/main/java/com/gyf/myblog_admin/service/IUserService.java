package com.gyf.myblog_admin.service;

import com.gyf.myblog_admin.domain.User;

public interface IUserService {

    User checkUser(String name, String password);

}
