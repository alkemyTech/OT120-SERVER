package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.enums.exception.OperationNotAllowedException;
import com.alkemy.ong.dto.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public ActivityDto update(ActivityDto activityDto, Long id) {
        Optional<Activity> activityOp = activityRepository.findById(id);

        if (!activityOp.isPresent()) {
            throw new OperationNotAllowedException("El id ingresado no existe.");
        }
        activityMapper.activityEntityUpdate(activityOp.get(), activityDto);
        Activity activityUpdated = activityRepository.save(activityOp.get());
        ActivityDto result = activityMapper.activityEntity2Dto(activityUpdated);

        return result;
    }
}
