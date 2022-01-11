package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.SlideDtoOrganization;
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.model.entity.Organization;
import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface ISlideService {

    void delete(Long id) throws EntityNotFoundException;

    SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException;

    List<SlideRequestDto> listAll();

    SlideResponseDto getById(Long id) throws EntityNotFoundException;

    List<SlideDtoOrganization> getOrganizationSlideList(Organization organization);

    List<SlideDtoOrganization> orderSlideDtoOrganization(List<SlideDtoOrganization> inputSlideDtoList);

}


