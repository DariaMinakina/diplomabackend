package ru.sfedu.diplomabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sfedu.diplomabackend.model.DiaryDayMental;
import ru.sfedu.diplomabackend.service.diaryday.DiaryDayMentalService;

import java.util.List;

@RestController
@RequestMapping(
        value = "/diaryday/mental",
        produces = { MediaType.APPLICATION_JSON_VALUE  })
public class DiaryDayMentalController {

    @Autowired
    private DiaryDayMentalService diaryDayMentalService;

    @RequestMapping(value="/add")
    public ModelAndView addDiaryDayMentalPage() {
        ModelAndView modelAndView = new ModelAndView("add-goal-form");
        modelAndView.addObject("diarydaymental", new DiaryDayMental());
        return modelAndView;
    }

    @RequestMapping(value="/add/process")
    public ModelAndView addingDiaryDayMental(@ModelAttribute DiaryDayMental diaryDayMental) {

        ModelAndView modelAndView = new ModelAndView("home");
        diaryDayMentalService.addDiaryDayMental(diaryDayMental);

        String message = "Diary Day Mental was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfGoals() {
        ModelAndView modelAndView = new ModelAndView("list-of-goals");

        List diaryDayMentalList = diaryDayMentalService.getDiaryDayMental();
        modelAndView.addObject("diaryDayMentalList", diaryDayMentalList);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.GET)
    public ModelAndView editDiaryDayMentalPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-goal-form");
        DiaryDayMental diaryDayMental = diaryDayMentalService.getByIdDiaryDayMental(id);
        modelAndView.addObject("DiaryDayMental",diaryDayMental);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingDiaryDayMental(@ModelAttribute DiaryDayMental diaryDayMental, @PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView("home");

        diaryDayMentalService.updateDiaryDayMental(diaryDayMental);

        String message = "DiaryDayMental was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteDiaryDayMental(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("home");
        diaryDayMentalService.deleteDiaryDayMental(id);
        String message = "DiaryDayMental was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
