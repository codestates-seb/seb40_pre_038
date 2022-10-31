package com.codestates.question;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.tag.Tag;
import com.codestates.tag.TagDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        Member member = new Member();
        member.setMemberId(questionPost.getMemberId());
        question.setMember(member);

        return question;
    }

//    default List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions) {
//        return questions.stream()
//                .map(this::questionToQuestionResponse)
//                .collect(Collectors.toList());
//    }

    default QuestionDto.Response questionToQuestionResponse(Question question) {
        Member member = question.getMember();
//        Tag tag = questionTag.getTag();

        return QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .view(question.getView())
                .vote(question.getVote())
                .tagList(question.getTagList())
//                .memberId(question.getMember().getMemberId())
//                .nickName(question.getMember().getNickName())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
//                .answers(question.getAnswers())
//                .comments(question.getComments())
//                .tags(tagToTagResponseDto())
//                .tags(question.getQuestionTags())
//                .tagResponseDto(tagToTagResponseDto(tag))
                .memberResponseDto(memberToMemberResponseDto(member))
                .build();
    }

//    default QuestionDto.Response questionToQuestionResponse(Question question) {
//        Member member = question.getMember();
//
//        QuestionDto.Response responseDto = new QuestionDto.Response(question.getQuestionId(),
//                question.getTitle(), question.getBody(),
//                question.getView(), question.getVote(),
//                question.getCreatedAt(), question.getModifiedAt(),
//                question.getAnswers(), question.getComments(), question.getTags(),
//                memberToMemberResponseDto(member));
//
//        return responseDto;
//    }

    MemberDto.Response memberToMemberResponseDto(Member member);

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
