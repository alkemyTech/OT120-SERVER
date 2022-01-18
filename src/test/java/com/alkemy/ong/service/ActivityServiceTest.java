package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.IActivityService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ActivityServiceTest {

    @MockBean
    private IActivityRepository activityRepository;

    @Autowired
    private IActivityService activityService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration{
        public IActivityService activityService(){
            return new ActivityServiceImpl();
        }
    }

    @Test
    public void testSaveActivity(){
        Activity activity = Activity.builder()
                .id(1L)
                .name("name")
                .content("content")
                .image("image")
                .build();

        Activity activitySaved = activityRepository.save(activity);

        assertNotNull(activitySaved);





    }


    }






