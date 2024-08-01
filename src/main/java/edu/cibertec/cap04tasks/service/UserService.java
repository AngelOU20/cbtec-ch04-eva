package edu.cibertec.cap04tasks.service;

import edu.cibertec.cap04tasks.dao.entity.UserEntity;

public interface UserService {
    UserEntity validateLogin(UserEntity user);
    UserEntity saveUser(UserEntity user);
}
