package edu.cibertec.cap04tasks.service;

import edu.cibertec.cap04tasks.dao.entity.Task;
import edu.cibertec.cap04tasks.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    List<Task> getAllTasks();
    List<Task> getAllTasksByUser(User user);
    Page<Task> getAllTasksByUser(User user, Pageable pageable);
    void deleteTask(int id);
    Task getTaskById(int id);
    Task updateTask(Task task);
}
