package edu.cibertec.cap04tasks.service;

import edu.cibertec.cap04tasks.dao.entity.User;

public interface UserService {
    User validateLogin(User user);
    User saveUser(User user);
}
