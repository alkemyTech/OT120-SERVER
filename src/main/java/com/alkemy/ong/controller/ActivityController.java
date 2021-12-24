package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> save(@Valid @RequestBody ActivityDto activityDto){
        ActivityDto activitySaved = activityService.save(activityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(activitySaved);
    }



}
