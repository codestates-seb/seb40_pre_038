package com.codestates.question;

import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.util.Pair;

import java.time.Duration;
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

        User user = new User();
        user.setUserId(questionPost.getUserId());
        question.setUser(user);

        return question;
    }

    default QuestionDto.Response questionToQuestionResponse(Question question) {
        User user = question.getUser();
        List<Comment> comments = question.getComments();

        List<CommentDto.Response> commentResponse
                = comments.stream().map(comment ->
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
                .build();
    }

    // Top Questions, All Questions에서 Get Question 대한 Mapper
    default QuestionDto.ResponseTopAll questionToQuestionResponseTopAll(Question question) {
        Pair<User, LocalDateTime> findActionUser = question.getAnswers().stream()
                .max((answer1, answer2) -> answer1.getModifiedAt().compareTo(answer2.getModifiedAt()))
                .map(answer -> Pair.of(answer.getUser(), answer.getModifiedAt()))
                .get();

        return QuestionDto.ResponseTopAll.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .problem(question.getProblem())
                .expect(question.getExpect())
                .tagList(question.getTagList())
                .view(question.getView())
                .vote(question.getVote())
                .answerCount(question.getAnswers().size())
                .actionUser(userToUserResponseDto(findActionUser.getFirst()))
                .accepted(
                        question.getAnswers().stream()
                                .filter(answer -> answer.getAnswerStatus().getStatusNumber() == 1)
                                .count() > 0
                )
                .build();
    }

    List<QuestionDto.ResponseTopAll> questionsToQuestionResponsesTopAll(List<Question> questions);

    UserDto.Response userToUserResponseDto(User user);
}
