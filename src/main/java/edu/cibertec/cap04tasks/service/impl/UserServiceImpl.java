package edu.cibertec.cap04tasks.service.impl;

import edu.cibertec.cap04tasks.dao.entity.UserEntity;
import edu.cibertec.cap04tasks.dao.repository.UserRepository;
import edu.cibertec.cap04tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity validateLogin(UserEntity user) {
        UserEntity validateUser =
                userRepository.findByUsernameAndPassword(
                        user.getUsername(),
                        user.getPassword()
                );
        return validateUser;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
