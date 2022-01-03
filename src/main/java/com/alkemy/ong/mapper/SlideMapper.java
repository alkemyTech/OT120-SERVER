package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.entity.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    public List<SlideDto> entity2DtoList(List<Slide> slides) {
        List<SlideDto> slidesDto = new ArrayList<>();

        for (Slide s : slides) {
            SlideDto dto = new SlideDto();
            dto.setImageUrl(s.getImageUrl());
            slidesDto.add(dto);
        }
        return slidesDto;
    }
}
