package com.codestates.question;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post questionPost);
    Question questionPatchToQuestion(QuestionDto.Patch questionPatch);
    QuestionDto.Response questionToQuestionResponse(Question question);
    List<QuestionDto.Response> questionsToQuestionResponses(List<Question> questions);

    Question questionVoteToQuestion(QuestionDto.Vote questionVote);
}
