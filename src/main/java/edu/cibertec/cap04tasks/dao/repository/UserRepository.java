package edu.cibertec.cap04tasks.dao.repository;

import edu.cibertec.cap04tasks.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}
