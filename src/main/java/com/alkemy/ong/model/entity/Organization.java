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
public class Organization {

  @Id
  @Column(name = "ORGANIZATIONS_ID")
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

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "PHONE")
  private Integer phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "WELCOME_TEXT", nullable = false)
  private String welcomeText;

  @Column(name = "ABOUT_US_TEXT")
  private String aboutUsText;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timeStamp;

  @Column(name = "SOFT_DELETE")
  private boolean softDelete;

}
