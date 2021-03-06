package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

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
@SQLDelete(sql = "UPDATE organization SET soft_delete=true WHERE id=?")
@Where(clause = "soft_delete=false")
@Table(name = "organization")
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORGANIZATION_ID", nullable = false)
  private long id;

  @Column(name = "org_name", nullable = false)
  private String name;

  @Column(name = "org_image_url", nullable = false)
  private String image;

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

  @Column(name = "org_facebook_url")
  private String facebook;

  @Column(name = "org_instagram_url")
  private String instagram;

  @Column(name = "org_linkedin_url")
  private String linkedin;

  @OneToMany(mappedBy = "organizationId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Slide> slideList = new ArrayList<>();
 
}
