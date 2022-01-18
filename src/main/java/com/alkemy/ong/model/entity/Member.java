package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "MEMBERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE MEMBERS SET SOFT_DELETE=true WHERE id=?")
@Where(clause = "SOFT_DELETE=false")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @Column(name = "MEMBERS_ID")
  private long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "TIMESTAMPS")
  @CreationTimestamp
  private Timestamp timestamps;

  @Column(name = "SOFT_DELETE")
  private boolean softDelete;

}
