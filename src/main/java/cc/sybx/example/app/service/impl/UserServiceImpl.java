package cc.sybx.example.app.service.impl;

import cc.sybx.example.app.mapper.UserMapper;
import cc.sybx.example.app.model.UserExample;
import cc.sybx.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : yuanzp
 * @Date : 2021/8/11 下午11:36
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String count() {
        return String.valueOf(userMapper.countByExample(new UserExample()));
    }
}
