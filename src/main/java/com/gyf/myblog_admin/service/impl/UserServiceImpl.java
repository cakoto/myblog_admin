package com.gyf.myblog_admin.service.impl;

import com.gyf.myblog_admin.dao.UserRepository;
import com.gyf.myblog_admin.service.IUserService;
import com.gyf.myblog_admin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }

}
