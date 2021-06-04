package ru.sfedu.diplomabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.sfedu.diplomabackend.Constants;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.security.JwtTokenProvider;
import ru.sfedu.diplomabackend.security.dto.GoalPostRequestDto;
import ru.sfedu.diplomabackend.service.goal.GoalService;
import ru.sfedu.diplomabackend.service.user.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/goals")
@AllArgsConstructor
public class GoalController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final GoalService goalService;

    private HttpClientErrorException createHttpException(HttpStatus status, String message) {
        return HttpClientErrorException.create(status, message, HttpHeaders.EMPTY, null, null);
    }

    @GetMapping
    public ResponseEntity<?> getGoalsByUserEmail(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(goalService.findByUserId(userService.findByEmail(jwtTokenProvider.getUsername(token)).get().getId())
                    .stream()
                    .collect(Collectors.toSet()),
                    HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGoal(@RequestHeader("Authorization") String token, @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var goal = goalService.getGoalById(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.GOAL_NOT_FOUNDED_CONST));
            return new ResponseEntity<>(goal, HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<?> creatGoal(@RequestHeader("Authorization") String token,
                                         @RequestBody GoalPostRequestDto goalPostRequestDto) {
        try {
            var goal = goalPostRequestDto.toGoal();
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            goal.setUserss(user);
            if (!goalService.addGoal(goal)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(goal);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@RequestHeader("Authorization") String token,
                                         @RequestBody Goal goalUpdated,
                                         @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var goal = goalService.getGoalById(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.GOAL_NOT_FOUNDED_CONST));
            goal.setDescription(goalUpdated.getDescription());
            goal.setPriority(goalUpdated.getPriority());
            if (!goalService.updateGoal(goal)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(goal);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@RequestHeader("Authorization") String token,
                                         @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var goal = goalService.getGoalById(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.GOAL_NOT_FOUNDED_CONST));
            if (!goalService.deleteGoal(id)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }


}