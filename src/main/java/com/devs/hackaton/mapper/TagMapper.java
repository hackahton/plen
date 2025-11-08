package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Tag.request.CreateTag;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.entity.Tag;

public class TagMapper {
    public static Tag toEntity(CreateTag request){
        return Tag.builder()
                .nome(request.nome())
                .build();
    }

    public static TagResponse toResponse(Tag tag){
        return TagResponse.builder()
                .id(tag.getId())
                .nome(tag.getNome())
                .build();
    }
}
