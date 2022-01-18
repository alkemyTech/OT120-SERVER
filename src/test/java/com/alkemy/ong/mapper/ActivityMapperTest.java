package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityMapperTest {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @MockBean
    private IActivityRepository activityRepository;

    @Test
    public void testActivityMapper(){
        //given
        Activity activity = Activity.builder()
                .id(1L)
                .name("Marisa")
                .content("mewdrlkwmrl")
                .image("imagen")
                .timestamps(new Timestamp(System.currentTimeMillis()))
                .build();

        //when
        ActivityDto activityDto = activityMapper.activityEntity2Dto(activity);

        //then
        assertAll(
                () -> {
                    assertEquals(activity.getName(),activityDto.getName());
                    assertEquals(activity.getContent(), activityDto.getContent());
                    assertEquals(activity.getImage(), activityDto.getImage());
                    assertEquals(activity.getTimestamps(), activityDto.getTimestamp());
                }
        );
                    }
}
