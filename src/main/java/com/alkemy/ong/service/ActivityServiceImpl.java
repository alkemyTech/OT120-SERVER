package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    Environment environment;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IActivityRepository activityRepository;

    private static final String NAME_NOT_BLANK_MESSAGE = "The Name' field cannot be empty!";
    private static final String CONTENT_NOT_BLANK_MESSAGE = "The content' field cannot be empty!";

    public ActivityDto save(ActivityDto activityDto){

        if(activityDto.getName().trim().isEmpty()){
            throw new NullPointerException(NAME_NOT_BLANK_MESSAGE);
        }
        if(activityDto.getContent().trim().isEmpty()){
            throw new NullPointerException(CONTENT_NOT_BLANK_MESSAGE);
        }
        Activity activityEntity = activityMapper.activityDto2Entity(activityDto);
        Activity activitySaved = this.activityRepository.save(activityEntity);
        ActivityDto result = activityMapper.activityEntity2Dto(activitySaved);
        return result;
    }
}
