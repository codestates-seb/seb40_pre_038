package com.codestates.answer.mapper;

import com.codestates.answer.dto.*;
import com.codestates.answer.entity.Answer;
import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.question.Question;
import com.codestates.user.service.UserService;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    Answer answerVoteDtoToAnswer(AnswerVoteDto answerVoteDto);

    Answer answerBestDtoToAnswer(AnswerBestDto answerBestDto);

    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);

    default Answer answerPostDtoToAnswer(long questionId, AnswerPostDto answerPostDto) {
        Answer answer = new Answer();
        answer.setBody(answerPostDto.getBody());

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
                .userInformation(userToUserResponseDto(user))
                .answerStatus(answer.getAnswerStatus())
                .questionId(answer.getQuestion().getQuestionId())
                .body(answer.getBody())
                .vote(answer.getVote())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .commentsWithUser(commentResponse)
                .actionStatus(answer.getActionStatus().getActionDescription())
                .build();
    }

    UserDto.Response userToUserResponseDto(User user);
}
