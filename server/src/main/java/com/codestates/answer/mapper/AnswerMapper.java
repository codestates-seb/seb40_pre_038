package com.codestates.answer.mapper;

import com.codestates.answer.dto.*;
import com.codestates.answer.entity.Answer;
import com.codestates.exception.user.dto.UserDto;
import com.codestates.exception.user.entity.User;
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

        User user = new User();
        user.setUserId(answerPostDto.getUserId());
        answer.setUser(user);

        Question question = new Question();
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        return answer;
    }

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        User user = answer.getUser();

        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .answerStatus(answer.getAnswerStatus())
                .questionId(answer.getQuestion().getQuestionId())
                .body(answer.getBody())
                .vote(answer.getVote())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .comments(answer.getComments())
                .userResponseDto(userToUserResponseDto(user))
                .build();
    }

    UserDto.Response userToUserResponseDto(User user);
}
