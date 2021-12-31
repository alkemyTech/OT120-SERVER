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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE organization SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "organization")
public class Organization {

  @Id
  @Column(name = "organization_id")
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "org_name", nullable = false)
  private String name;

  @Column(name = "org_image_url", nullable = false)
  private String image;

  @Column(name = "org_facebook_url")
  private String facebook;

  @Column(name = "org_instagram_url")
  private String instagram;

  @Column(name = "org_linkedin_url")
  private String linkedin;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private Integer phone;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "welcome_text", nullable = false)
  private String welcomeText;

  @Column(name = "about_us_text")
  private String aboutUsText;

  @Column(name = "timestamp")
  @CreationTimestamp
  private Timestamp timeStamp;

  @Column(name = "soft_delete")
  private boolean softDelete;





}
