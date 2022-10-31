package com.codestates.question;

import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.tag.Tag;
import com.codestates.tag.TagDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    //Question questionPostToQuestion(QuestionDto.Post questionPost);
    Question questionPatchToQuestion(QuestionDto.Patch questionPatch);

    //QuestionDto.Response questionToQuestionResponse(Question question);
    List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions);

    Question questionVoteToQuestion(QuestionDto.Vote questionVote);

    Question questionViewToQuestion(QuestionDto.View questionView);

    default Question questionPostToQuestion(QuestionDto.Post questionPost) {
        Question question = new Question();
        question.setTitle(questionPost.getTitle());
        question.setBody(questionPost.getBody());
        question.setTagBody(questionPost.getTagBody());

        User user = new User();
        user.setUserId(questionPost.getUserId());
        question.setUser(user);

        return question;
    }

//    default List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions) {
//        return questions.stream()
//                .map(this::questionToQuestionResponse)
//                .collect(Collectors.toList());
//    }

    default QuestionDto.Response questionToQuestionResponse(Question question) {
        User user = question.getUser();
//        Tag tag = questionTag.getTag();

        return QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .view(question.getView())
                .vote(question.getVote())
                .tagList(question.getTagList())
//                .userId(question.getUser().getUserId())
//                .nickName(question.getUser().getNickName())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
//                .answers(question.getAnswers())
//                .comments(question.getComments())
//                .tags(tagToTagResponseDto())
//                .tags(question.getQuestionTags())
//                .tagResponseDto(tagToTagResponseDto(tag))
                .userResponseDto(userToUserResponseDto(user))
                .build();
    }

//    default QuestionDto.Response questionToQuestionResponse(Question question) {
//        User user = question.getUser();
//
//        QuestionDto.Response responseDto = new QuestionDto.Response(question.getQuestionId(),
//                question.getTitle(), question.getBody(),
//                question.getView(), question.getVote(),
//                question.getCreatedAt(), question.getModifiedAt(),
//                question.getAnswers(), question.getComments(), question.getTags(),
//                userToUserResponseDto(user));
//
//        return responseDto;
//    }

    UserDto.Response userToUserResponseDto(User user);

    TagDto.Response tagToTagResponseDto(Tag tag);



//    default List<TagDto.Response> tagsToTagResponseDtos(Set<QuestionTag> questionTags)
//    {
//        return questionTags
//                .stream()
//                .map(questionTag -> TagDto.Response
//                        .builder()
//                        .
//                        .build())
//                .collect(Collectors.toList());
//    }

}
