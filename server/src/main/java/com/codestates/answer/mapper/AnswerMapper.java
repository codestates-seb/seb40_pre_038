package com.codestates.answer.mapper;

import com.codestates.answer.dto.*;
import com.codestates.answer.entity.Answer;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import com.codestates.dto.question.Question;
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
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setAnswerId(answer.getAnswerId());
        answerResponseDto.setAnswerStatus(answer.getAnswerStatus());
        answerResponseDto.setQuestionId(answer.getQuestion().getQuestionId());
        answerResponseDto.setBody(answer.getBody());
        answerResponseDto.setVote(answer.getVote());

        answerResponseDto.setCreatedAt(answer.getCreatedAt());
        answerResponseDto.setModifiedAt(answer.getModifiedAt());

        Member member = answer.getMember();
        answerResponseDto.setMemberResponseDto(memberToMemberResponseDto(member));

        return answerResponseDto;
    }

    MemberResponseDto memberToMemberResponseDto(Member member);
}
