package com.codestates.reply;

import com.codestates.reply.entity.Reply;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    Reply replyPostToReply(ReplyDto.Post replyPost);
    Reply replyPatchToReply(ReplyDto.Patch replyPatch);
    ReplyDto.Response replyToReplyResponse(Reply reply);
    List<ReplyDto.Response> repliesToReplyResponses(List<Reply> replies);
}
