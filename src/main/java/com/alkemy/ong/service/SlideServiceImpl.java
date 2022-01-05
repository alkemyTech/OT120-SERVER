package com.alkemy.ong.service;


import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.exception.NotFoundExceptions;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ISlideService;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";

    @Autowired
    private ISlideRepository slideRepository;


    @Autowired
    private SlideMapper slideMapper;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!slideRepository.existsById(id)) {
            throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public List<SlideRequestDto> listAll() {
        if (slideRepository.findAll() == null) {
            throw new NotFoundExceptions(SLIDE_NOT_FOUND_MESSAGE);
        }

        List<SlideRequestDto> listOrdered = slideMapper.entity2DtoList(slideRepository.findAll());

        Collections.sort(listOrdered, new Comparator<SlideRequestDto>() {
            @Override
            public int compare(SlideRequestDto one, SlideRequestDto two) {
                return one.getOrder() - two.getOrder();
            }
        });

        return listOrdered;
    }


    @Override
    public SlideResponseDto update(long id, SlideRequestDto slideReqDto) throws EntityNotFoundException {
        Optional<Slide> result = slideRepository.findById(id);

        if (result.isPresent()) {

            Slide slide = slideMapper.slideReqDto2Entity(slideReqDto);
            slide.setImageUrl(slideReqDto.imageUrl);
            slide.setText(slideReqDto.text);
            slide.setOrder(slideReqDto.order);

            Organization organization = organizationRepository.getById(slideReqDto.organizationId);
            slide.setOrganizationId(organization);
            slide.setId(id);
            slideRepository.save(slide);
            return slideMapper.slideEntity2Dto(slide);

        } else {
            throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
        }
    }



    public SlideResponseDto getById(Long id) throws EntityNotFoundException{
        Slide slide = slideRepository.getById(id);
        if(slide==null){
            throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
        }
        return slideMapper.slideEntity2Dto(slide);
        }
}