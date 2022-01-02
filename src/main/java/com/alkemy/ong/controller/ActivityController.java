package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.LoginRequestDto;
import com.alkemy.ong.dto.TokenDto;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.service.AuthenticationService;
import com.alkemy.ong.service.abstraction.IActivityService;
import com.alkemy.ong.service.abstraction.IUserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> save(@Valid @RequestBody ActivityDto activityDto, BindingResult errors) {
        if (errors.hasFieldErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<ObjectError> errorList = errors.getAllErrors();
            for (ObjectError error : errorList) {
                stringBuilder.append(error.getDefaultMessage());
            }

        }
        ActivityDto activitySaved = activityService.save(activityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(activitySaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> update(@RequestBody ActivityDto activity, @PathVariable Long id) {

        ActivityDto activityUpdated = activityService.update(activity, id);

        return ResponseEntity.status(HttpStatus.OK).body(activityUpdated);
    }

}
