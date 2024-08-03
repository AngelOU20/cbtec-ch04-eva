package edu.cibertec.cap04tasks.controller;

import edu.cibertec.cap04tasks.dao.entity.User;
import edu.cibertec.cap04tasks.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String loginView() {
        log.debug("Ingresando al login");
        return "login";
    }

    @RequestMapping("loginAction")
    public ModelAndView loginAction(User user) {
        User ue = userService.validateLogin(user);

        if (ue == null) {
            log.error("Error en el login");
            return new ModelAndView("login",
                    "msgError",
                    "Usuario y clave no existen. Vuelva a intentar!");
        }

        log.info("Usuario ingresando a la aplicación " + ue.getUsername());
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("currentUser", ue);

        return modelAndView;
    }

    @RequestMapping("register")
    public ModelAndView registerView() {
        log.debug("Ingresando al register");
        return new ModelAndView("register", "userBean", new User());
    }

    @RequestMapping("registerAction")
    public ModelAndView registerAction(@Valid @ModelAttribute("userBean") User user,
                                       BindingResult result) {
        if (result.hasErrors()) {
            log.error("Error al registrar un user");
            return new ModelAndView("register", "userBean", user);
        }

        userService.saveUser(user);
        log.info("Usuario registrado con exito" + user.getUsername());
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping("logoutAction")
    public String logout(SessionStatus sessionStatus) {
        log.debug("Cerrar sesión");
        sessionStatus.setComplete();
        return "login";
    }
}
