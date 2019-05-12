package com.jinbang.mapper;

import com.jinbang.model.User;
import java.util.List;

public interface UserMapper {
    int userReg(User user);
    int updateUserById(User user);
    int deleteUserById(int uid);
    User loadByUserName(String name);
    User getUserById(int uid);
    List<User> getAll();
    List<String> getNames();
}
