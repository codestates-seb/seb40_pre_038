package com.codestates.tag;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.question.Question;
import com.codestates.question.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final QuestionRepository questionRepository;

    public TagService(TagRepository tagRepository, QuestionRepository questionRepository) {
        this.tagRepository = tagRepository;
        this.questionRepository = questionRepository;
    }

    public Tag createTag(Tag tag) {

//        Set<String> list = new LinkedHashSet<>();
        List<String> list = new ArrayList<>();

        Arrays.stream(tag.getBody().split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .map(String::toLowerCase)
                .forEach(list::add);

//        tag.setTagSet(list);
        tag.setTagList(list);

        return tagRepository.save(tag);
    }

//    public Tag createTag(Tag tag) {
//
////        Set<String> list = new LinkedHashSet<>();
//        List<String> list = new ArrayList<>();
//
//        Arrays.stream(tag.getBody().split(","))
//                .map(a -> Arrays.stream(a.trim()
//                                .split(" "))
//                        .flatMap(b -> Arrays.stream(b.split(", "))))
//                .flatMap(a -> a)
//                .distinct()
//                .map(String::toLowerCase)
//                .forEach(list::add);
//
////        tag.setTagSet(list);
//        tag.setTagList(list);
//
//        return tagRepository.save(tag);
//    }

    public Tag updateTag(Tag tag, long tagId) {
        Tag findTag = findVerifiedTag(tagId);

//        Set<String> list = new LinkedHashSet<>();
        List<String> list = new ArrayList<>();

        Arrays.stream(tag.getBody().split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .map(String::toLowerCase)
                .forEach(list::add);

        System.out.println(list);
        findTag.setBody(tag.getBody());
//        findTag.setTagSet(list);
//        System.out.println(findTag.getTagSet());
        findTag.setTagList(list);
        System.out.println(findTag.getTagList());

        return tagRepository.save(findTag);
    }

    public Tag findVerifiedTag(long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag = // 에러 핸들링 상의 해야 됨
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findTag;
    }



//    public List<Tag> createTags(List<Tag> tags) {
//        return tags.stream()
//                .map(tagRepository::save)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteTags(Question question) {
//        long questionId = question.getQuestionId();
//        List<Tag> tags = tagRepository.findAllByQuestionId(questionId);
//
//        tags.forEach(tag -> {
//            System.out.println("Deleted TagId: " + tag.getTagId());
//            tag.setTagStatus(Tag.TagStatus.TAG_NOT_EXIST);
//            tagRepository.save(tag);
//        });
//    }

//    public List<Tag> findVerifiedTags(Question question) {
//        return tagRepository.findAllByQuestionAndTagStatus(question, Tag.TagStatus.TAG_EXISTS);
//    }
}
