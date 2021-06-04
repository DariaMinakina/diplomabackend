package ru.sfedu.diplomabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.sfedu.diplomabackend.Constants;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;
import ru.sfedu.diplomabackend.security.JwtTokenProvider;
import ru.sfedu.diplomabackend.security.dto.DiaryDayPostPhysicsRequestDto;
import ru.sfedu.diplomabackend.service.diaryday.DiaryDayPhysicsService;
import ru.sfedu.diplomabackend.service.user.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/physics")
@AllArgsConstructor
public class DiaryDayPhysicsController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final DiaryDayPhysicsService diaryDayPhysicsService;

    private HttpClientErrorException createHttpException(HttpStatus status, String message) {
        return HttpClientErrorException.create(status, message, HttpHeaders.EMPTY, null, null);
    }

    @GetMapping
    public ResponseEntity<?> getDiaryDayPhysicsByUserEmail(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(diaryDayPhysicsService.findDiaryDayPhysicsByUserId(userService.findByEmail(jwtTokenProvider.getUsername(token)).get().getId())
                    .stream()
                    .collect(Collectors.toSet()),
                    HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaryDayPhysics(@RequestHeader("Authorization") String token, @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var diaryDaPhysics = diaryDayPhysicsService.getByIdDiaryDayPhysics(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_PHYSICS_NOT_FOUNDED_CONST));
            return new ResponseEntity<>(diaryDayPhysicsService, HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<?> createDiaryDayPhysics(@RequestHeader("Authorization") String token,
                                                  @RequestBody DiaryDayPostPhysicsRequestDto diaryDayPostPhysicsRequestDto) {
        try {
            var diaryDayPhysics = diaryDayPostPhysicsRequestDto.toDiaryDayPhysics();
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            diaryDayPhysics.setUsers(user);
            if (!diaryDayPhysicsService.addDiaryDayPhysics(diaryDayPhysics)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(diaryDayPhysics);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiaryDayPhysics(@RequestHeader("Authorization") String token,
                                                  @RequestBody DiaryDayPhysics diaryDayPhysicsUpdated,
                                                  @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var diaryDayPhysics = diaryDayPhysicsService.getByIdDiaryDayPhysics(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_PHYSICS_NOT_FOUNDED_CONST));
            diaryDayPhysics.setDescription(diaryDayPhysicsUpdated.getDescription());
            diaryDayPhysics.setHeight(diaryDayPhysicsUpdated.getHeight());
            diaryDayPhysics.setWeight(diaryDayPhysicsUpdated.getWeight());
            if (!diaryDayPhysicsService.updateDiaryDayPhysics(diaryDayPhysics)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(diaryDayPhysics);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiaryDayMental(@RequestHeader("Authorization") String token,
                                                  @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var diaryDayPhysics = diaryDayPhysicsService.getByIdDiaryDayPhysics(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_PHYSICS_NOT_FOUNDED_CONST));
            if (!diaryDayPhysicsService.deleteDiaryDayPhysics(id)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }
}
