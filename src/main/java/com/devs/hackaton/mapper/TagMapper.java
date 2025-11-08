package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Tag.request.CreateTag;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.entity.Tag;

import java.util.List;

public class TagMapper {
    public static Tag toEntity(CreateTag request){
        return Tag.builder()
                .name(request.name())
                .build();
    }

    public static TagResponse toResponse(Tag tag){
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public static List<TagResponse> toTagResponseList(List<Tag> tags){
        return tags.stream()
                .map(TagMapper::toResponse)
                .toList();
    }
}