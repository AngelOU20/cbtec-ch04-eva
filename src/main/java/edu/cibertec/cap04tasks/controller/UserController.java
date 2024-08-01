package edu.cibertec.cap04tasks.controller;

import edu.cibertec.cap04tasks.dao.entity.UserEntity;
import edu.cibertec.cap04tasks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("currentUser")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String loginView() {
        return "login";
    }

    @RequestMapping("loginAction")
    public ModelAndView loginAction(UserEntity user) {
        UserEntity ue = userService.validateLogin(user);

        if (ue == null) {
            return new ModelAndView("login",
                    "msgError",
                    "Usuario y clave no existen. Vuelva a intentar!");
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("currentUser", ue);

        return modelAndView;
    }

    @RequestMapping("register")
    public ModelAndView registerView() {
        return new ModelAndView("register", "userBean", new UserEntity());
    }

    @RequestMapping("registerAction")
    public ModelAndView registerAction(@Valid @ModelAttribute("userBean") UserEntity user,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("register", "userBean", user);
        }

        userService.saveUser(user);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("currentUser", user);

        return modelAndView;
    }

    @RequestMapping("logoutAction")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "login";
    }
}
