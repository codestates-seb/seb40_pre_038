package com.codestates.question;

import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;
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

        return QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .view(question.getView())
                .vote(question.getVote())
//                .memberId(question.getMember().getMemberId())
//                .nickName(question.getMember().getNickName())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
//                .answers(question.getAnswers())
//                .comments(question.getComments())
                .tags(question.getTags())
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

    MemberResponseDto memberToMemberResponseDto(Member member);
}
