package com.alkemy.ong.seeder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;

@Component
public class ActivitySeeder implements CommandLineRunner {

	@Autowired
	IActivityRepository activityRepository;

	@Override
	public void run(String... args) throws Exception {
		loadActivityData();
	}

	private void loadActivityData() {
		if (activityRepository.count() == 0) {
			Activity activity1 = new Activity(1, "Utiles escolares para escuelas municipales", "Utiles escolares para escuelas municipales (...)", "image.org/image_1.png", null, false);
			Activity activity2 = new Activity(1, "Juguetes por Navidad", "Juguetes por Navidad (...)", "image.org/image_2.png", null, false);
			activityRepository.save(activity1);
			activityRepository.save(activity2);
		}
	}
}
