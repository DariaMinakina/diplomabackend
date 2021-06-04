package ru.sfedu.diplomabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.sfedu.diplomabackend.Constants;
import ru.sfedu.diplomabackend.model.DiaryDayMental;
import ru.sfedu.diplomabackend.security.JwtTokenProvider;
import ru.sfedu.diplomabackend.security.dto.DiaryDayMentalPostRequestDto;
import ru.sfedu.diplomabackend.service.diaryday.DiaryDayMentalService;
import ru.sfedu.diplomabackend.service.user.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/mental")
@AllArgsConstructor
public class DiaryDayMentalController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final DiaryDayMentalService diaryDayMentalService;

    private HttpClientErrorException createHttpException(HttpStatus status, String message) {
        return HttpClientErrorException.create(status, message, HttpHeaders.EMPTY, null, null);
    }

    @GetMapping
    public ResponseEntity<?> getDiaryDayMentalByUserEmail(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(diaryDayMentalService.findDiaryDayMentalByUserId(userService.findByEmail(jwtTokenProvider.getUsername(token)).get().getId())
                    .stream()
                    .collect(Collectors.toSet()),
                    HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaryDayMental(@RequestHeader("Authorization") String token, @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var diaryDayMental = diaryDayMentalService.getByIdDiaryDayMental(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_MENTAL_NOT_FOUNDED_CONST));
            return new ResponseEntity<>(diaryDayMental, HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<?> createDiaryDayMental(@RequestHeader("Authorization") String token,
                                       @RequestBody DiaryDayMentalPostRequestDto diaryDayMentalPostRequestDto) {
        try {
            var diaryDayMental = diaryDayMentalPostRequestDto.toDiaryDayMental();
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            diaryDayMental.setUsers(user);
            if (!diaryDayMentalService.addDiaryDayMental(diaryDayMental)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(diaryDayMental);
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiaryDayMental(@RequestHeader("Authorization") String token,
                                         @RequestBody DiaryDayMental diaryDayMentalUpdated,
                                         @PathVariable long id) {
        try {
            var user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                    .orElseThrow(() -> createHttpException(HttpStatus.NOT_FOUND, Constants.USER_NOT_FOUND_CONST));
            var diaryDayMental = diaryDayMentalService.getByIdDiaryDayMental(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_MENTAL_NOT_FOUNDED_CONST));
            diaryDayMental.setDescription(diaryDayMentalUpdated.getDescription());
            diaryDayMental.setBurnoutResult(diaryDayMentalUpdated.getBurnoutResult());
            diaryDayMental.setDepressionResult(diaryDayMentalUpdated.getDepressionResult());
            if (!diaryDayMentalService.updateDiaryDayMental(diaryDayMental)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok(diaryDayMental);
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
            var diaryDayMental = diaryDayMentalService.getByIdDiaryDayMental(id).orElseThrow(() ->
                    createHttpException(HttpStatus.NOT_FOUND, Constants.DIARY_DAY_MENTAL_NOT_FOUNDED_CONST));
            if (!diaryDayMentalService.deleteDiaryDayMental(id)) {
                throw createHttpException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SMTH_GOES_WRONG_CONST);
            }
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getStatusText(), exception.getStatusCode());
        }
    }



}
