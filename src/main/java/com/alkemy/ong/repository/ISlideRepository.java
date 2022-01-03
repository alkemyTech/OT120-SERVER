package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
=======

>>>>>>> c08ee483849c29cef24dbaf657093e712912151c
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {

<<<<<<< HEAD
 @Query("SELECT s FROM Slides s ORDER BY s.order asc;")
    public List<Slide> listAllByOrder();

=======

    List<Slide> findAllByOrganizationId(Organization organization);
>>>>>>> c08ee483849c29cef24dbaf657093e712912151c

}
