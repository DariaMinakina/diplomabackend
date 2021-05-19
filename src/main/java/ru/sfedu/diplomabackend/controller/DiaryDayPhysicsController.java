package ru.sfedu.diplomabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sfedu.diplomabackend.model.DiaryDayMental;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.service.diaryday.DiaryDayPhysicsService;
import ru.sfedu.diplomabackend.service.goal.GoalService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/diaryday/physics",
        produces = { MediaType.APPLICATION_JSON_VALUE  })
public class DiaryDayPhysicsController {

    @Autowired
    private DiaryDayPhysicsService diaryDayPhysicsService;

    @RequestMapping(value="/add")
    public ModelAndView addDiaryDayPhysicsPage() {
        ModelAndView modelAndView = new ModelAndView("add-goal-form");
        modelAndView.addObject("DiaryDayPhysics", new DiaryDayPhysics());
        return modelAndView;
    }

    @RequestMapping(value="/add/process")
    public ModelAndView addingDiaryDayPhysics(@ModelAttribute DiaryDayPhysics diaryDayPhysics) {

        ModelAndView modelAndView = new ModelAndView("home");
        diaryDayPhysicsService.addDiaryDayPhysics(diaryDayPhysics);

        String message = "DiaryDayPhysics was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfDiaryDayPhysics() {
        ModelAndView modelAndView = new ModelAndView("list-of-goals");

        List diaryDayPhysicsList = diaryDayPhysicsService.getDiaryDayPhysics();
        modelAndView.addObject("DiaryDayPhysicsList", diaryDayPhysicsList);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editDiaryDayPhysicsPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-goal-form");
        DiaryDayPhysics diaryDayPhysics = diaryDayPhysicsService.getByIdDiaryDayPhysics(id);
        modelAndView.addObject("DiaryDayPhysics",diaryDayPhysicsService);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingDiaryDayPhysics(@ModelAttribute DiaryDayPhysics diaryDayPhysics, @PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("home");

        diaryDayPhysicsService.updateDiaryDayPhysics(diaryDayPhysics);

        String message = "DiaryDayPhysics was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteDiaryDayPhysics(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        diaryDayPhysicsService.deleteDiaryDayPhysics(id);
        String message = "DiaryDayPhysics was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
