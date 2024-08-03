package edu.cibertec.cap04tasks.dao.repository;

import edu.cibertec.cap04tasks.dao.entity.Task;
import edu.cibertec.cap04tasks.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);
    Page<Task> findByUser(User user, Pageable pageable);
}
