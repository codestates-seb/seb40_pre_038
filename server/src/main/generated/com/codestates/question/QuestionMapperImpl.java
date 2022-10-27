package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.entity.Comment;
import com.codestates.tag.Tag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-27T10:39:13+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostToQuestion(QuestionDto.Post questionPost) {
        if ( questionPost == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPost.getTitle() );
        question.setBody( questionPost.getBody() );

        return question;
    }

    @Override
    public Question questionPatchToQuestion(QuestionDto.Patch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatch.getQuestionId() );
        question.setTitle( questionPatch.getTitle() );
        question.setBody( questionPatch.getBody() );

        return question;
    }

    @Override
    public QuestionDto.Response questionToQuestionResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        List<Answer> answers = null;
        List<Tag> tags = null;
        long questionId = 0L;
        String title = null;
        String body = null;
        int vote = 0;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        List<Answer> list = question.getAnswers();
        if ( list != null ) {
            answers = new ArrayList<Answer>( list );
        }
        List<Tag> list1 = question.getTags();
        if ( list1 != null ) {
            tags = new ArrayList<Tag>( list1 );
        }
        questionId = question.getQuestionId();
        title = question.getTitle();
        body = question.getBody();
        vote = question.getVote();
        createdAt = question.getCreatedAt();
        modifiedAt = question.getModifiedAt();

        long memberId = 0L;
        List<Comment> replies = null;

        QuestionDto.Response response = new QuestionDto.Response( questionId, memberId, title, body, vote, createdAt, modifiedAt, answers, replies, tags );

        return response;
    }

    @Override
    public List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.Response> list = new ArrayList<QuestionDto.Response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponse( question ) );
        }

        return list;
    }

    @Override
    public Question questionVoteToQuestion(QuestionDto.Vote questionVote) {
        if ( questionVote == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionVote.getQuestionId() );
        question.setVote( questionVote.getVote() );

        return question;
    }
}
