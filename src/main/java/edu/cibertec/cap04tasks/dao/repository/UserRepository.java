package edu.cibertec.cap04tasks.dao.repository;

import edu.cibertec.cap04tasks.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsernameAndPassword(String username, String password);
}
