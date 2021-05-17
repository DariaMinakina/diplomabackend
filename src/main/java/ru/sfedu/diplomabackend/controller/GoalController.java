package ru.sfedu.diplomabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.Priority;
import ru.sfedu.diplomabackend.service.GoalService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/goal",
        produces = { MediaType.APPLICATION_JSON_VALUE  })
public class GoalController {

    @Autowired
    private GoalService goalService;


    @RequestMapping(value="/goal/add")
    public ModelAndView addGoalPage() {
        ModelAndView modelAndView = new ModelAndView("add-goal-form");
        modelAndView.addObject("goal", new Goal());
        return modelAndView;
    }

    @RequestMapping(value="/goal/add/process")
    public ModelAndView addingGoal(@ModelAttribute Goal goal) {

        ModelAndView modelAndView = new ModelAndView("home");
        goalService.addGoal(goal);

        String message = "Goal was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/goal/list")
    public ModelAndView listOfGoals() {
        ModelAndView modelAndView = new ModelAndView("list-of-goals");

        List goals = goalService.getGoals();
        modelAndView.addObject("goals", goals);

        return modelAndView;
    }

    @RequestMapping(value="/goal/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editGoalPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-goal-form");
        Goal goal = goalService.getById(id);
        modelAndView.addObject("goal",goal);
        return modelAndView;
    }

    @RequestMapping(value="/goal/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingGoal(@ModelAttribute Goal goal, @PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("home");

        goalService.updateGoal(goal);

        String message = "Goal was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/goal/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteGoal(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        goalService.deleteGoal(id);
        String message = "Goal was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}