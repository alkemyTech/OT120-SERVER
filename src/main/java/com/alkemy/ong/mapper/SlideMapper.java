package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDtoGet;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SlideMapper {

    public List<SlideDtoGet> toSlideDtoGetList(List<Slide> slides) {
        List<SlideDtoGet> dtos = new ArrayList<>();

        if (slides != null) {
            dtos = slides.stream().map(slide -> {
                return new SlideDtoGet(slide.getImageUrl(), slide.getText(), slide.getOrder(), slide.getOrganizationId().getId());
            }).collect(Collectors.toList());
        }
        return dtos;
     }
}
