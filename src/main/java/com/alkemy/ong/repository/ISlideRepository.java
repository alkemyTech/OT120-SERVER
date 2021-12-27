package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {

    @Query("SELECT s FROM Slide s WHERE s.organizationId = :org ORDER BY s.slideOrder")
    List<Slide> findAllByOrganizationId(Organization organization);

}
