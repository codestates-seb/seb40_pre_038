package com.codestates.restdocs.question;

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
                                parameterWithName("questionId").description("질문 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("질문 댓글 본대")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("댓글 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("댓글 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("댓글 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("댓글 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("유저 명성"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("댓글 본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("댓글 타입")
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
                                parameterWithName("answerId").description("답변 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("질문 댓글 본문")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("댓글 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("댓글 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("댓글 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("댓글 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("유저 명성"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("댓글 본문"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("댓글 타입")
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
                                parameterWithName("commentId").description("댓글 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 번호"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("수정된 댓글")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.commentId").type((JsonFieldType.NUMBER)).description("댓글 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("댓글 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("댓글 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("댓글 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("댓글 유저 명성"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("수정된 댓글"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.commentType").type(JsonFieldType.STRING).description("댓글 타입")
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
                "Question-Comment should not be empty",
                LocalDateTime.now(),
                LocalDateTime.now(),
                CommentType.QUESTION
        );

        Question question2 = new Question(
                2L,
                new User(2L,
                        "Stub_Potato2",
                        "stub_email2@user.com",
                        1234567890),
                "Title2",
                "Problem2 should have more than 20 characters",
                "Expect2 should have more than 20 characters",
                List.of("tag2", "body2"),
                0, 0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                Question.ActionStatus.ACTION_ASKED
        );

        List<QuestionDto.Response> responses = List.of(

                new QuestionDto.Response(1L,
                        userMapper.userToUserResponseDto(new User()),
                        "Title1",
                        "Problem1 should have more than 20 characters",
                        "Expect1 should have more than 20 characters",
                        List.of("tag1", "body1"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null,
                        "asked"
                ),


                new QuestionDto.Response(2L,
                        userMapper.userToUserResponseDto(new User()),
                        "Title2",
                        "Problem2 should have more than 20 characters",
                        "Expect2 should have more than 20 characters",
                        List.of("tag2", "body2"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null,
                        "asked"
                )

        );

        Page<Question> memberPage = new PageImpl<>(List.of());

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt())).willReturn(memberPage);

        given(questionMapper.questionsToQuestionResponses(Mockito.anyList())).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/questions")
                                .param("page", "1")
                                .param("size", "15")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions.andExpect(status().isOk())
                .andDo(document("get-questions",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data[].userInformation.userId").type((JsonFieldType.NUMBER)).description("질문 유저 번호"),
                                        fieldWithPath("data[].userInformation.nickName").type((JsonFieldType.STRING)).description("질문 유저 이름"),
                                        fieldWithPath("data[].userInformation.email").type((JsonFieldType.STRING)).description("질문 유저 이메일 주소"),
                                        fieldWithPath("data[].userInformation.reputation").type((JsonFieldType.NUMBER)).description("유저 명성"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data[].problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("data[].expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("data[].tagList").type(JsonFieldType.ARRAY).description("태그 리스트"),
                                        fieldWithPath("data[].view").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data[].vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("data[].commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data[].actionStatus").type(JsonFieldType.STRING).description("질문 상태"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 회원 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
                                )
                        )
                ));

    }

    @Test
    void deleteQuestionTest() throws Exception {

        long questionId = 1L;

        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        mockMvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/questions/{questionId}/delete", questionId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                pathParameters(
                                        parameterWithName("questionId").description("질문 번호")
                                )
                        )
                );
    }
}
