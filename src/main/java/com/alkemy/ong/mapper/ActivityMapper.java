package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.model.entity.Activity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActivityMapper {

    public Activity activityDto2Entity(ActivityDto activityDto) {
        Activity activityEntity = new Activity();
        activityEntity.setName(activityDto.getName());
        activityEntity.setContent(activityDto.getContent());
        activityEntity.setImage(activityDto.getImage());
        activityEntity.setTimestamps(new Timestamp(System.currentTimeMillis()));
        return activityEntity;
    }

    public ActivityDto activityEntity2Dto(Activity activityEntity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activityEntity.getId());
        activityDto.setName(activityEntity.getName());
        activityDto.setContent(activityEntity.getContent());
        activityDto.setImage(activityEntity.getImage());
        activityDto.setTimestamp(activityEntity.getTimestamps());
        return activityDto;
    }

    public void activityEntityUpdate(Activity activity, ActivityDto activityDto) {
        activityDto.setName(activity.getName());
        activityDto.setContent(activity.getContent());
        activityDto.setImage(activity.getImage());
        activityDto.setTimestamp(activity.getTimestamps());
    }
}
