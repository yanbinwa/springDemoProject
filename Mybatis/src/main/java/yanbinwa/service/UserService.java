package yanbinwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yanbinwa.dao.UserDao;
import yanbinwa.domain.User;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public boolean addUser(String username, String password){
        return userDao.insertUser(username, password)==1?true:false;
    }
    
    public User addUserWithBackId(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.insertUserWithBackId(user);
        return user;
    }
    
}
