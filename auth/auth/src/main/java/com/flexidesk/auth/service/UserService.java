package com.flexidesk.auth.service;

import com.flexidesk.auth.entity.User;

public interface UserService {
    User registerUser(User user);
    User getUserByEmail(String email);

}
