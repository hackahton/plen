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
        Tag tag = TagMapper.toEntity(request);

        try{
            tagRepository.save(tag);
            return TagMapper.toResponse(tag);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

   public TagResponse findTagById(Long id) {
       Tag tag = findTagEntityById(id);
       return TagMapper.toResponse(tag);
   }

   public Tag findTagEntityById(Long id) {
       return tagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found"));
   }

   public List<TagResponse> findAllTags() {
       return tagRepository.findAll().stream().map(TagMapper::toResponse).toList();
   }
}
