package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPatchToQuestion(QuestionDto.Patch questionPatch);

    List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions);

    Question questionVoteToQuestion(QuestionDto.Vote questionVote);

    Question questionViewToQuestion(QuestionDto.View questionView);

    default Question questionPostToQuestion(QuestionDto.Post questionPost) {
        Question question = new Question();
        question.setTitle(questionPost.getTitle());
        question.setProblem(questionPost.getProblem());
        question.setExpect(questionPost.getExpect());
        question.setTagBody(questionPost.getTagBody());

        return question;
    }

    default QuestionDto.Response questionToQuestionResponse(Question question) {
        User user = question.getUser();
        List<Comment> comments = question.getComments();

        List<CommentDto.Response> commentResponse
                = comments.stream()
                .filter(a -> a.getCommentType().equals(CommentType.QUESTION))
                .map(comment ->
                        new CommentDto.Response(comment.getCommentId(),
                                userToUserResponseDto(comment.getUser()),
                                comment.getBody(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt(),
                                comment.getCommentType()))
                .collect(Collectors.toList());

        return QuestionDto.Response.builder()
                .questionId(question.getQuestionId())
                .userInformation(userToUserResponseDto(user))
                .title(question.getTitle())
                .problem(question.getProblem())
                .expect(question.getExpect())
                .view(question.getView())
                .vote(question.getVote())
                .tagList(question.getTagList())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .answerCount(question.getAnswers().size())
                .commentsWithUser(commentResponse)
                .actionStatus(question.getActionStatus().getActionDescription())
                .build();
    }

    // Top Questions, All Questions에서 Get Question 대한 Mapper
    default QuestionDto.ResponseTopAll questionToQuestionResponseTopAll(Question question) {
        Answer findActionAnswer = question.getAnswers().isEmpty() ? null : question.getAnswers().stream()
                .max((answer1, answer2) -> answer1.getModifiedAt().compareTo(answer2.getModifiedAt()))
                .get();

        User actionUser = question.getUser();
        String actionStatus = question.getActionStatus().getActionDescription();
        LocalDateTime actionTime = question.getModifiedAt();
        if (findActionAnswer != null && findActionAnswer.getModifiedAt().compareTo(actionTime) > 0) {
            actionUser = findActionAnswer.getUser();
            actionStatus = findActionAnswer.getActionStatus().getActionDescription();
            actionTime = findActionAnswer.getModifiedAt();
        }

        return QuestionDto.ResponseTopAll.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .problem(question.getProblem())
                .expect(question.getExpect())
                .tagList(question.getTagList())
                .view(question.getView())
                .vote(question.getVote())
                .answerCount(question.getAnswers().size())
                .accepted(
                        question.getAnswers().stream()
                                .filter(answer -> answer.getAnswerStatus().getStatusNumber() == 1)
                                .count() > 0
                )
                .status(actionStatus)
                .actionUser(userToUserResponseDto(actionUser))
                .actionTime(actionTime)
                .build();
    }

    List<QuestionDto.ResponseTopAll> questionsToQuestionResponsesTopAll(List<Question> questions);

    UserDto.Response userToUserResponseDto(User user);
}
