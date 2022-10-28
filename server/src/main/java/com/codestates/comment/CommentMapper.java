package com.codestates.comment;

import com.codestates.member.dto.MemberResponseDto;
import com.codestates.comment.entity.Comment;
import com.codestates.member.entity.Member;
import com.codestates.question.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    MemberResponseDto memberToMemberResponseDto(Member member);
//    Comment commentPostToComment(CommentDto.Post commentPost);

    default Comment commentPostToComment(CommentDto.Post commentPost) {
        Comment comment = new Comment();
        comment.setBody(commentPost.getBody());

        Member member = new Member();
        member.setMemberId(commentPost.getMemberId());
        comment.setMember(member);

        return comment;
    }
    Comment commentPatchToComment(CommentDto.Patch commentPatch);
//    CommentDto.Response commentToCommentResponse(Comment comment);

    default CommentDto.Response commentToCommentResponse(Comment comment) {
        Member member = comment.getMember();

        return CommentDto.Response.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPostId())
                .memberResponseDto(memberToMemberResponseDto(member))
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .commentType(comment.getCommentType())
                .build();
    }
    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
}
