package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Activity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

@DataJpaTest
public class ActivityRepositoryTest {

    @Autowired
    private IActivityRepository activityRepository;

    @Test
    public void saveActivityTest(){

        //given
        Activity activity = Activity.builder()
                .name("nombre")
                .content("content")
                .image("imagen0")
                .timestamps(new Timestamp(System.currentTimeMillis()))
                .build();

        //when
        activityRepository.save(activity);

        //then
        Assertions.assertThat(activity.getId()).isGreaterThan(0);

    }

    @Test
    public void updateActivityTest(){
        //given
        Activity activity = Activity.builder()
                .id(1L)
                .name("nombre")
                .content("content")
                .image("imagen0")
                .timestamps(new Timestamp(System.currentTimeMillis()))
                .build();

        activityRepository.save(activity);

        //when
        Activity activityUpdate = activityRepository.findById(1L).get();
        activityUpdate.setName("new Name");
        activityRepository.save(activityUpdate);

        //then
        Assertions.assertThat(activityUpdate.getName()).isEqualTo("new Name");
    }
}
