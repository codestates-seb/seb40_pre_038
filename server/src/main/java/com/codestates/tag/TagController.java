package com.codestates.tag;

import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @Secured("ROLE_USER")
    @PostMapping("/add")
    public ResponseEntity postTag(@Valid @RequestBody TagDto.Post tagPost) {
        Tag tag = mapper.tagPostToTag(tagPost);
        Tag createTag = tagService.createTag(tag);
        TagDto.Response response = mapper.tagToTagResponse(createTag);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @Secured("ROLE_USER")
    @PatchMapping("/{tag_id}/edit")
    public ResponseEntity patchTag(@Valid @RequestBody TagDto.Patch tagPatch,
                                   @PathVariable("tag_id") @Positive long tagId) {
        tagPatch.setTagId(tagId);
        Tag tag = mapper.tagPatchToTag(tagPatch);
        Tag patchTag = tagService.updateTag(tag, tagPatch.getTagId());
        TagDto.Response response = mapper.tagToTagResponse(patchTag);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
