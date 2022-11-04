package com.codestates.comment;

import com.codestates.user.dto.UserDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment commentPostToComment(CommentDto.Post commentPost);
    /*default Comment commentPostToComment(CommentDto.Post commentPost) {
        Comment comment = new Comment();
        comment.setBody(commentPost.getBody());

        *//*User user = new User();
        user.setUserId(commentPost.getUserId());
        comment.setUser(user);*//*

        return comment;
    }*/
    Comment commentPatchToComment(CommentDto.Patch commentPatch);

    default CommentDto.Response commentToCommentResponse(Comment comment) {
        User user = comment.getUser();

        return CommentDto.Response.builder()
                .commentId(comment.getCommentId())
                .userResponseDto(userToUserResponseDto(user))
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .commentType(comment.getCommentType())
                .build();
    }
    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
    UserDto.Response userToUserResponseDto(User user);
}
