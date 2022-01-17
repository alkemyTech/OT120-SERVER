package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORIES")
public class Category extends RepresentationModel<Category> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORIES_ID")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "IMAGE")
  private String image;

  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;

  @Column(name = "SOFT_DELETE")
  private boolean softDelete;

}
