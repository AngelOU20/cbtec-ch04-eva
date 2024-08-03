package edu.cibertec.cap04tasks.service.impl;

import edu.cibertec.cap04tasks.dao.entity.Task;
import edu.cibertec.cap04tasks.dao.entity.User;
import edu.cibertec.cap04tasks.dao.repository.TaskRepository;
import edu.cibertec.cap04tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Override
    public Page<Task> getAllTasksByUser(User user, Pageable pageable) {
        return taskRepository.findByUser(user, pageable);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);  // Guardar la tarea actualizada
    }
}
