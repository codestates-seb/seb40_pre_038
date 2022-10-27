package com.codestates.comment;

import com.codestates.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostToComment(CommentDto.Post commentPost);
    Comment commentPatchToComment(CommentDto.Patch commentPatch);
    CommentDto.Response commentToCommentResponse(Comment comment);
//    List<CommentDto.Response> repliesToCommentResponses(List<Comment> replies);
}
