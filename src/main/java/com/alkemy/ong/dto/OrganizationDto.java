package com.alkemy.ong.dto;

import javax.validation.constraints.Positive ;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizationDto {

  private long id;

  @Size(min= 3, max = 255)
  private String name;

  @Size(min = 4, max = 255)
  private String image;

  @Positive
  @Size(min = 8, max = 11)
  private Integer phone;

  @Size(min = 8, max = 255)
  private String address;

  private String email;
  private String welcomeText;
  private String aboutUsText;

  private String facebook;
  private String instagram;
  private String linkedin;

  private List<SlideDtoOrganization> slideDtoList;

}


