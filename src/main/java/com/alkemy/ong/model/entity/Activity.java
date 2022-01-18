package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SQLDelete(sql = "UPDATE ACTIVITIES SET SOFT_DELETE=true WHERE id=?")
@Where(clause = "SOFT_DELETE=false")
@Table(name = "ACTIVITIES")
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @Column(name = "ACTIVITIES_ID")
  private long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "CONTENT", nullable = false)
  private String content;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @Column(name = "TIMESTAMPS")
  @CreationTimestamp
  private Timestamp timestamps;

  @Column(name = "SOFT_DELETE")
  private boolean softDelete;


}
