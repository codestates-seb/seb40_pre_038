package com.codestates.answer.mapper;

import com.codestates.answer.dto.*;
import com.codestates.answer.entity.Answer;
import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.question.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Comment> comments = answer.getComments();

        List<CommentDto.Response> commentResponse
                = comments.stream().map(comment ->
                new CommentDto.Response(comment.getCommentId(),
                        userToUserResponseDto(comment.getUser()),
                        comment.getBody(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt(),
                        comment.getCommentType()))
                .collect(Collectors.toList());

        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .userResponseDto(userToUserResponseDto(user))
                .answerStatus(answer.getAnswerStatus())
                .questionId(answer.getQuestion().getQuestionId())
                .body(answer.getBody())
                .vote(answer.getVote())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .commentsWithUser(commentResponse)
                .build();
    }

    UserDto.Response userToUserResponseDto(User user);
}
