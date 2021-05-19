package ru.sfedu.diplomabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sfedu.diplomabackend.model.User;
import ru.sfedu.diplomabackend.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/user",
        produces = { MediaType.APPLICATION_JSON_VALUE  })
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add")
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("add-goal-form");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value="/add/process")
    public ModelAndView addingGoal(@ModelAttribute User user) {

        ModelAndView modelAndView = new ModelAndView("home");
        userService.addUser(user);

        String message = "User was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("list-of-goals");

        List users = userService.getUsers();
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-goal-form");
        User user = userService.getById(id);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingUser(@ModelAttribute User user, @PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("home");

        userService.updateUser(user);

        String message = "User was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteGoal(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        userService.deleteUser(id);
        String message = "User was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
