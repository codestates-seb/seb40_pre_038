package com.codestates.tag;

import com.codestates.question.Question;
import com.codestates.question.QuestionDto;
import com.codestates.question.QuestionTag;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "Spring")
public interface TagMapper {

    Tag tagPostToTag(TagDto.Post tagPost);
//    default Tag tagPostToTag(TagDto.Post tagPost) {
//        Tag tag = new Tag();
//        Set<String> list = new LinkedHashSet<>();
//
//        Arrays.stream(tagPost.getBody().split(","))
//                .map(a -> Arrays.stream(a.trim()
//                                .split(" "))
//                        .flatMap(b -> Arrays.stream(b.split(", "))))
//                .flatMap(a -> a)
//                .forEach(list::add);
//
//        tag.setTagSet(list);
//
//        return tag;
//    }

    Tag tagPatchToTag(TagDto.Patch questionPatch);

//    default Tag tagPatchToTag(TagDto.Patch tagPatch) {
//        Tag tag = new Tag();
//        Set<String> updatedList = new LinkedHashSet<>();
//
//        Arrays.stream(tagPatch.getBody().split(","))
//                .map(a -> Arrays.stream(a.trim()
//                                .split(" "))
//                        .flatMap(b -> Arrays.stream(b.split(", "))))
//                .flatMap(a -> a)
//                .forEach(updatedList::add);
//
//        tag.setTagSet(updatedList);
//
//        return tag;
//    }

    default TagDto.Response tagToTagResponse(Tag tag) {

        return TagDto.Response.builder()
                .tagId(tag.getTagId())
//                .tagSet(tag.getTagSet())
                .tagList(tag.getTagList())
                .build();
    }

//    default TagDto.Response tagToTagResponse(QuestionTag questionTag) {
//        Set<String> tagSet = questionTag.getTag().getTagSet();
//
//        return TagDto.Response.builder()
//                .tagSet(tagSet)
//                .build();
//    }


}
