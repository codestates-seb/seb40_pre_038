package com.codestates.answer.mapper;

import com.codestates.answer.dto.*;
import com.codestates.answer.entity.Answer;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.question.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    //Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    //AnswerResponseDto answerToAnswerResponseDto(Answer answer);

    Answer answerVoteDtoToAnswer(AnswerVoteDto answerVoteDto);

    Answer answerBestDtoToAnswer(AnswerBestDto answerBestDto);

    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerPostDto answerPostDto) {
        Answer answer = new Answer();
        answer.setBody(answerPostDto.getBody());

        Member member = new Member();
        member.setMemberId(answerPostDto.getMemberId());
        answer.setMember(member);

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
    }

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        Member member = answer.getMember();

        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .answerStatus(answer.getAnswerStatus())
                .questionId(answer.getQuestion().getQuestionId())
                .body(answer.getBody())
                .vote(answer.getVote())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .memberResponseDto(memberToMemberResponseDto(member))
                .build();
    }

    MemberDto.Response memberToMemberResponseDto(Member member);
}
