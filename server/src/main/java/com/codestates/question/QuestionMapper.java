package com.codestates.question;

import com.codestates.answer.service.AnswerService;
import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
//import com.codestates.tag.Tag;
//import com.codestates.tag.TagDto;
import org.mapstruct.Mapper;

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
                = comments.stream().map(comment -> new CommentDto.Response(
                        comment.getCommentId(), userToUserResponseDto(comment.getUser()),
                comment.getBody(), comment.getCreatedAt(), comment.getModifiedAt(), comment.getCommentType())).collect(Collectors.toList());

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
                //.commentInformation(usersToUserResponseDtos(commentUsers))
                //.comments(question.getComments())
                .answerCount(question.getAnswers().size())

                .commentsWithUser(commentResponse)

                .build();
    }

    UserDto.Response userToUserResponseDto(User user);
    //CommentDto.Response commentToCommentResponse(Comment comment);
}
