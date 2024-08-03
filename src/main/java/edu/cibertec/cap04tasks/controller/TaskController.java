package edu.cibertec.cap04tasks.controller;

import edu.cibertec.cap04tasks.dao.entity.Task;
import edu.cibertec.cap04tasks.dao.entity.User;
import edu.cibertec.cap04tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(@SessionAttribute("currentUser") User currentUser) {
        return currentUser;
    }

//    @RequestMapping("/tasksList")
//    public String listTasks(@ModelAttribute("currentUser") User currentUser, Model model) {
//        List<Task> tasks = taskService.getAllTasksByUser(currentUser);
//        model.addAttribute("tasks", tasks);
//        return "tasks";
//    }

    @RequestMapping("/tasksList")
    public String listTasks(@ModelAttribute("currentUser") User currentUser,
                            Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "6") int size) {
        Page<Task> tasks = taskService.getAllTasksByUser(currentUser,
                PageRequest.of(page,size));
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskBean", new Task());
        return "tasks";
    }

    @RequestMapping(value = "taskCreate", method = RequestMethod.POST)
    public String taskCrearAccion(@Valid @ModelAttribute("taskBean") Task task,
                                  BindingResult result,
                                  @ModelAttribute("currentUser") User currentUser,
                                  Model model) {

        if (result.hasErrors()) {
            List<Task> tasks = taskService.getAllTasksByUser(currentUser);
            model.addAttribute("tasks", tasks);
            return "tasks";
        }

        task.setUser(currentUser);  // Asociar la tarea con el usuario actual
        taskService.addTask(task);
        return "redirect:/tasksList";
    }


    @RequestMapping(value = "taskDelete", method = RequestMethod.GET)
    public String taskEliminar(@RequestParam("id") int id) {
        taskService.deleteTask(id);
        return "redirect:/tasksList";
    }

    @RequestMapping(value = "taskEdit", method = RequestMethod.GET)
    public String taskEditForm(@RequestParam("id") int id, Model model) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            model.addAttribute("taskBean", task);
            return "taskEdit";
        }
        return "redirect:/tasksList";
    }

    // Procesa la actualización de la tarea
    @RequestMapping(value = "taskUpdate", method = RequestMethod.POST)
    public String taskUpdateAccion(@Valid @ModelAttribute("taskBean") Task task,
                                   BindingResult result,
                                   @SessionAttribute("currentUser") User currentUser,
                                   Model model) {

        System.out.println("currentUser: " + currentUser.getId());

        if (result.hasErrors()) {
            List<Task> tasks = taskService.getAllTasksByUser(currentUser);
            model.addAttribute("tasks", tasks);
            return "taskEdit";
        }

        // Verificar que currentUser tiene un id válido
        if (currentUser == null || currentUser.getId() == 0) {
            model.addAttribute("errorMessage", "Current user is not valid");
            return "taskEdit";
        }

        // Agregar logs para depuración
        System.out.println("currentUser: " + currentUser.getId());

        // Cargar la tarea existente desde la base de datos
        Task existingTask = taskService.getTaskById(task.getId());
        if (existingTask != null) {
            System.out.println("existingTask found: " + existingTask.getId());

            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setUser(currentUser);  // Asociar la tarea con el usuario actual

            taskService.updateTask(existingTask);
        } else {
            // Manejar el caso donde la tarea no existe (opcional)
            model.addAttribute("errorMessage", "Task not found");
            return "taskEdit";
        }

        return "redirect:/tasksList";
    }
}

