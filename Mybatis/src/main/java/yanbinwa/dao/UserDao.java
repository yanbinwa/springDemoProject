package yanbinwa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yanbinwa.domain.User;
import yanbinwa.mapper.UserMapper;


@Repository
public class UserDao {
    
    @Autowired
    private UserMapper userMapper;
    
    public int insertUser(String username, String password){
        return userMapper.insertUser(username, password);
    }
    
    public int insertUserWithBackId(User user){    
        return userMapper.insertUserWithBackId(user);
    }
    
}