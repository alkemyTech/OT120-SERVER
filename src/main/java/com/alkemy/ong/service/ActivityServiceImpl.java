package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private IActivityRepository activityRepository;

    public ActivityDto save(ActivityDto activityDto){
        Activity activityEntity = activityMapper.activityDto2Entity(activityDto);
        Activity activitySaved = this.activityRepository.save(activityEntity);
        ActivityDto result = activityMapper.activityEntity2Dto(activitySaved);
        return result;
    }
}
