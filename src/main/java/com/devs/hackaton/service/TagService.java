package com.devs.hackaton.service;

import com.devs.hackaton.dto.Tag.request.CreateTag;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.mapper.TagMapper;
import com.devs.hackaton.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagResponse createTag(CreateTag request) {
        log.info("Creating tag: {}", request);

        if (tagRepository.findByName(request.name()).isPresent()) {
            log.error("Tag already exists");
            throw new IllegalArgumentException("Tag already exists");
        }

        Tag tag = tagRepository.save(TagMapper.toEntity(request));
        log.info("Tag created: {}", tag);

        return TagMapper.toResponse(tag);
    }

    public List<TagResponse> findAll() {
        return tagRepository.findAll().stream()
                .map(TagMapper::toResponse)
                .toList();
    }
}
