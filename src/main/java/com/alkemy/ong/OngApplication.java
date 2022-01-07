package com.alkemy.ong;


import com.alkemy.ong.model.entity.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.IntStream;


@SpringBootApplication
public class OngApplication {

    public static void main(String[] args) {

        SpringApplication.run(OngApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ICategoryRepository categoryRepository) {
        return args -> IntStream.rangeClosed(1, 30).forEach(i -> {
            Category category = new Category();
            category.setName("user" + i);
            category.setImage(i + "jpg");
            category.setDescription(i + "decripcion Lorem ipsum");
            categoryRepository.save(category);
        });
    }
}
