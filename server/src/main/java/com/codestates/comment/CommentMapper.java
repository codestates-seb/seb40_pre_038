package com.codestates.comment;

import com.codestates.comment.entity.Comment;
import com.codestates.question.Question;
import com.codestates.question.QuestionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostToComment(CommentDto.Post commentPost);
    Comment commentPatchToComment(CommentDto.Patch commentPatch);
    CommentDto.Response commentToCommentResponse(Comment comment);
    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
}
