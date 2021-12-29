package com.alkemy.ong.dto;

import javax.validation.constraints.Positive ;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizationDto {

  private Long id;

  @Size(min= 3, max = 255)
  private String name;

  @Size(min = 4, max = 255)
  private String image;

  @Positive
  @Size(min = 8, max = 11)
  private Integer phone;

  @Size(min = 8, max = 255)
  private String address;

  private List<SlideDtoGet> organizationSlides;

  private String facebook;
  private String instagram;
  private String linkedin;

}
