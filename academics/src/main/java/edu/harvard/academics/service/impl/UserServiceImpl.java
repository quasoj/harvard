package edu.harvard.academics.service.impl;

import edu.harvard.academics.entity.User;
import edu.harvard.academics.mapper.UserMapper;
import edu.harvard.academics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(User user) {
        return userMapper.getUserByUsernameAndPassword(user);
    }

}
