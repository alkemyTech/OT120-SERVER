package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.ActivityDto;

public interface IActivityService {
    ActivityDto save(ActivityDto activityDto);

    ActivityDto update(ActivityDto activity, Long id);
}
