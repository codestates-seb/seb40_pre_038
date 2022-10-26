package com.codestates.answer.mapper;

import com.codestates.answer.dto.AnswerPatchDto;
import com.codestates.answer.dto.AnswerPostDto;
import com.codestates.answer.dto.AnswerResponseDto;
import com.codestates.answer.dto.AnswerVoteDto;
import com.codestates.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

    Answer answerVoteDtoToAnswer(AnswerVoteDto answerVoteDto);

    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
}
