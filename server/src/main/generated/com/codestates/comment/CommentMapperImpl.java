package com.codestates.comment;

import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-27T10:39:14+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostToComment(CommentDto.Post commentPost) {
        if ( commentPost == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentType( commentPost.getCommentType() );
        comment.setBody( commentPost.getBody() );

        return comment;
    }

    @Override
    public Comment commentPatchToComment(CommentDto.Patch commentPatch) {
        if ( commentPatch == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentId( commentPatch.getCommentId() );
        comment.setBody( commentPatch.getBody() );

        return comment;
    }

    @Override
    public CommentDto.Response commentToCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        long commentId = 0L;
        String body = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        CommentType commentType = null;
        long postId = 0L;

        commentId = comment.getCommentId();
        body = comment.getBody();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
        commentType = comment.getCommentType();
        postId = comment.getPostId();

        long memberId = 0L;

        CommentDto.Response response = new CommentDto.Response( commentId, body, createdAt, modifiedAt, memberId, commentType, postId );

        return response;
    }
}
