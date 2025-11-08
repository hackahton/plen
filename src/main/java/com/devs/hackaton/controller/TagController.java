package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Tag.request.CreateTag;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody CreateTag request){
        try {
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(tagService.createTag(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível criar uma tag");
        }
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> findAllTags(){
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(tagService.findAllTags());
    }
}
