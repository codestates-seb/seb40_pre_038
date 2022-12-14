package com.codestates.restdocs;

import com.codestates.answer.controller.AnswerController;
import com.codestates.answer.service.AnswerService;
import com.codestates.auth.jwt.JwtTokenizer;
import com.codestates.auth.utils.CustomAuthorityUtils;
import com.codestates.comment.CommentDto;
import com.codestates.comment.CommentMapper;
import com.codestates.comment.CommentService;
import com.codestates.comment.entity.Comment;
import com.codestates.comment.entity.CommentType;
import com.codestates.question.Question;
import com.codestates.question.QuestionDto;
import com.codestates.question.QuestionMapper;
import com.codestates.question.QuestionService;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.user.mapper.UserMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.codestates.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class CommentControllerRestDoc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper questionMapper;

    @MockBean
    private AnswerController answerController;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentService commentService;


    @MockBean
    private UserMapper userMapper;

    @MockBean
    private JwtTokenizer jwtTokenizer;

    @MockBean
    private CustomAuthorityUtils customAuthorityUtils;



    @BeforeEach
    private void init() {
        UserDto.Response userResponse =
                new UserDto.Response(1L,
                        "Stub_Potato",
                        "stub_email@user.com",
                        1234567890);


        given(userMapper.userToUserResponseDto(Mockito.any())).willReturn(userResponse);

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());

    }

    @Test
    void postQuestionCommentTest() throws Exception {

        long questionId = 1;

        CommentDto.Post post = new CommentDto.Post(
                "Question-Comment should not be empty"
        );

        String content = gson.toJson(post);

        CommentDto.Response response =
                new CommentDto.Response(1L,
                        userMapper.userToUserResponseDto(new User()),
                                "Question-Comment should not be empty",
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                CommentType.QUESTION
                        );

        given(commentMapper.commentPostToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());

        given(commentService.createQuestionComment(Mockito.any(Comment.class),anyLong())).willReturn(new Comment());

        given(commentMapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/questions/{questionId}/comments/add", questionId)
                                .param("postId", "1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.body").value(post.getBody()))
                .andDo(document(
                        "post-question-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("questionId").description("?????? ??????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ?????? ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("?????? ??????"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("?????? ?????? ????????? ??????"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("????????? ?????? ??????"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    void postAnswerCommentTest() throws Exception {

        long answerId = 1;

        CommentDto.Post post = new CommentDto.Post(
                "Answer-Comment should not be empty"
        );

        String content = gson.toJson(post);

        CommentDto.Response response =
                new CommentDto.Response(1L,
                        userMapper.userToUserResponseDto(new User()),
                        "Answer-Comment should not be empty",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        CommentType.ANSWER
                );

        given(commentMapper.commentPostToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());

        given(commentService.createAnswerComment(Mockito.any(Comment.class),anyLong())).willReturn(new Comment());

        given(commentMapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/answers/{answerId}/comments/add", answerId)
//                                .param("postId", "1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.body").value(post.getBody()))
                .andDo(document(
                        "post-answer-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("answerId").description("?????? ??????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("?????? ?????? ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("?????? ??????"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("?????? ?????? ????????? ??????"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("????????? ?????? ??????"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    void patchCommentTest() throws Exception {

        long commentId = 1;

        CommentDto.Patch patch = new CommentDto.Patch(
                1,
                "Changed comment should not be empty"
        );

        String content = gson.toJson(patch);

        CommentDto.Response response =
                new CommentDto.Response(1L,
                        userMapper.userToUserResponseDto(new User()),
                        "Changed comment should not be empty",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        CommentType.QUESTION
                );

        given(commentMapper.commentPatchToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());

        given(commentService.updateComment(Mockito.any(Comment.class), anyLong())).willReturn(new Comment());

        given(commentMapper.commentToCommentResponse(Mockito.any(Comment.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/api/comments/{commentId}/edit", commentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.commentId").value(patch.getCommentId()))
                .andExpect(jsonPath("$.data.body").value(patch.getBody()))
                .andDo(document(
                        "patch-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("commentId").description("?????? ??????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("????????? ??????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("?????? ?????????"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("?????? ??????"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("?????? ?????? ????????? ??????"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("????????? ?????? ??????"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }

    @Test
    void getCommentsTest() throws Exception {

        Comment comment1 = new Comment(
                1L,
                new User(1L,
                        "Stub_Potato1",
                        "stub_email1@user.com",
                        1234567890),
                "Question-Comment1 should not be empty",
                LocalDateTime.now(),
                LocalDateTime.now(),
                CommentType.QUESTION
        );

        Comment comment2 = new Comment(
                2L,
                new User(2L,
                        "Stub_Potato1",
                        "stub_email1@user.com",
                        1234567890),
                "Question-Comment2 should not be empty",
                LocalDateTime.now(),
                LocalDateTime.now(),
                CommentType.ANSWER
        );

        List<CommentDto.Response> responses = List.of(
                new CommentDto.Response(1L,
                        new UserDto.Response(1L,
                                "Stub_Potato1",
                                "stub_email1@user.com",
                                1234567890),
//                        userMapper.userToUserResponseDto(new User()),
                        "Changed comment should not be empty",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        CommentType.QUESTION
                ),

                new CommentDto.Response(1L,
                        new UserDto.Response(2L,
                                "Stub_Potato2",
                                "stub_email2@user.com",
                                1234567890),
//                        userMapper.userToUserResponseDto(new User()),
                        "Changed comment should not be empty",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        CommentType.QUESTION
                )
        );

        Page<Comment> commentPage = new PageImpl<>(List.of());

        given(commentService.findComments(Mockito.anyInt(), Mockito.anyInt())).willReturn(commentPage);

        given(commentMapper.commentsToCommentResponses(Mockito.anyList())).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/comments")
                                .param("page", "1")
                                .param("size", "15")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions.andExpect(status().isOk())
                .andDo(document("get-comments",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("?????????"),
                                parameterWithName("size").description("????????? ??????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("data[].commentId").type((JsonFieldType.NUMBER)).description("?????? ??????"),
                                        fieldWithPath("data[].userInformation.userId").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].userInformation.nickName").type((JsonFieldType.STRING)).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].userInformation.email").type((JsonFieldType.STRING)).description("?????? ?????? ????????? ??????"),
                                        fieldWithPath("data[].userInformation.reputation").type((JsonFieldType.NUMBER)).description("?????? ?????? ??????"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("????????? ?????? ??????"),
                                        fieldWithPath("data[].commentType").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("??? ??? ???"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???")
                                )
                        )
                ));

    }

    @Test
    void deleteQuestionTest() throws Exception {

        long commentId = 1L;

        doNothing().when(commentService).deleteComment(Mockito.anyLong());

        mockMvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/comments/{commentId}/delete", commentId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-comment",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                pathParameters(
                                        parameterWithName("commentId").description("?????? ??????")
                                )
                        )
                );
    }
}
