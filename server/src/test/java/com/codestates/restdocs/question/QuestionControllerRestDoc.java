package com.codestates.restdocs.question;

import com.codestates.answer.controller.AnswerController;
import com.codestates.answer.service.AnswerService;
import com.codestates.auth.jwt.JwtTokenizer;
import com.codestates.auth.utils.CustomAuthorityUtils;
import com.codestates.comment.CommentDto;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.codestates.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
//@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class QuestionControllerRestDoc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerController answerController;

    @MockBean
    private AnswerService answerService;


    @MockBean
    private UserMapper userMapper;

    @MockBean
    private JwtTokenizer jwtTokenizer;

    @MockBean
    private CustomAuthorityUtils customAuthorityUtils;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper questionMapper;

    @BeforeEach
    private void init() {
        UserDto.Response userResponse =
                new UserDto.Response(1L,
                        "Stub_Potato",
                        "stub_email@user.com",
                        1234567890);


        given(userMapper.userToUserResponseDto(Mockito.any())).willReturn(userResponse);

//        CommentDto.Response commentResponse =
//                new CommentDto.Response(1,
//                        userResponse,
//                        "Comment12345123451234512345",
//                        LocalDateTime.now(),
//                        LocalDateTime.now(),
//                        CommentType.QUESTION);
    }

    @Test
    void postQuestionTest() throws Exception {

        QuestionDto.Post post = new QuestionDto.Post(
                "Title",
                "Problem should have more than 20 characters",
                "Expect should have more than 20 characters"
                , "tag, body"
                , List.of("tag", "body")
        );

        String content = gson.toJson(post);

        QuestionDto.Response response =
                new QuestionDto.Response(1L,
                        userMapper.userToUserResponseDto(new User(1L,
                                "Stub_Potato",
                                "stub_email@user.com",
                                1234567890)),
                        "Title",
                        "Problem should have more than 20 characters",
                        "Expect should have more than 20 characters",
                        List.of("tag", "body"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null,
                        "asked"
                );

        response.setUserInformation(userMapper.userToUserResponseDto(new User(1L,
                "Stub_Potato",
                "stub_email_@user.com",
                1234567890)));

        given(questionMapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        post("/api/questions/add")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.userInformation.userId").value(post.getUserId()))
                .andExpect(jsonPath("$.data.problem").value(post.getProblem()))
                .andExpect(jsonPath("$.data.expect").value(post.getExpect()))
                .andExpect(jsonPath("$.data.tagList[0:2]").value(new ArrayList<>(post.getTags())))
                .andDo(document(
                        "post-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 번호"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("tagBody").type(JsonFieldType.STRING).description("태그 바디"),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그 리스트")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("질문 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("질문 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("질문 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("질문 유저 명성"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("data.expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("data.tagList").type(JsonFieldType.ARRAY).description("태그 리스트"),
                                        fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));
    }

    @Test
    void patchQuestionTest() throws Exception {

        long questionId = 1;

        QuestionDto.Patch patch = new QuestionDto.Patch(
                1,
                "Changed title",
                "Changed problem should have more than 20 characters",
                "Changed expect should have more than 20 characters",
                "Changed, tag, body",
                List.of("changed", "tag", "body")
        );

        String content = gson.toJson(patch);

        QuestionDto.Response response =
                new QuestionDto.Response(1L,
                        userMapper.userToUserResponseDto(new User(1L,
                                "Stub_Potato",
                                "stub_email@user.com",
                                1234567890)),
                        "Changed title",
                        "Changed problem should have more than 20 characters",
                        "Changed expect should have more than 20 characters",
                        List.of("changed", "tag", "body"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null,
                        "modified"
                );

        given(questionMapper.questionPatchToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/api/questions/{questionId}/edit", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(patch.getQuestionId()))
//                .andExpect(jsonPath("$.data.userInformation.userId").value(patch.getUserId()))
                .andExpect(jsonPath("$.data.problem").value(patch.getProblem()))
                .andExpect(jsonPath("$.data.expect").value(patch.getExpect()))
                .andExpect(jsonPath("$.data.tagList[0:]").value(new ArrayList<>(patch.getTags())))
                .andDo(document(
                        "patch-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 번호"),
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 번호"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("수정된 제목"),
                                        fieldWithPath("problem").type(JsonFieldType.STRING).description("수정된 문제"),
                                        fieldWithPath("expect").type(JsonFieldType.STRING).description("수정된 기대"),
                                        fieldWithPath("tagBody").type(JsonFieldType.STRING).description("수정된 태그 바디"),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("수정된 태그 리스트")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("질문 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("질문 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("질문 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("질문 유저 명성"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("수정된 제목"),
                                        fieldWithPath("data.problem").type(JsonFieldType.STRING).description("수정된 문제"),
                                        fieldWithPath("data.expect").type(JsonFieldType.STRING).description("수정된 기대"),
                                        fieldWithPath("data.tagList").type(JsonFieldType.ARRAY).description("수정된 태그 리스트"),
                                        fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));
    }

    @Test
    void getQuestionTest() throws Exception {

        long questionId = 1;

        QuestionDto.Response response =
                new QuestionDto.Response(1L,
                        userMapper.userToUserResponseDto(new User(1L,
                                "Stub_Potato",
                                "stub_email@user.com",
                                1234567890)),
                        "Title",
                        "Problem should have more than 20 characters",
                        "Expect should have more than 20 characters",
                        List.of("tag", "body"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null,
                        "asked"
                );

        given(questionService.findQuestion(anyLong())).willReturn(new Question());

        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/questions/{questionId}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.questionId").value(response.getQuestionId()))
                .andExpect(jsonPath("$.data.userInformation.userId").value(response.getUserInformation().getUserId()))
                .andExpect(jsonPath("$.data.problem").value(response.getProblem()))
                .andExpect(jsonPath("$.data.expect").value(response.getExpect()))
                .andExpect(jsonPath("$.data.tagList[0:]").value(new ArrayList<>(response.getTagList())))
                .andDo(document(
                        "get-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 번호")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("질문 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("질문 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("질문 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("질문 유저 명성"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("data.expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("data.tagList").type(JsonFieldType.ARRAY).description("태그 리스트"),
                                        fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));
    }

    @Test
    void getQuestionsTest() throws Exception {

        Question question1 = new Question(
                1L,
                new User(1L,
                        "Stub_Potato1",
                        "stub_email1@user.com",
                        1234567890),
                "Title1",
                "Problem1 should have more than 20 characters",
                "Expect1 should have more than 20 characters",
                List.of("tag1", "body1"),
                0, 0,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                Question.ActionStatus.ACTION_ASKED
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
                        new UserDto.Response(1L,
                                "Stub_Potato1",
                                "stub_email1@user.com",
                                1234567890),
//                        userMapper.userToUserResponseDto(new User()),
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
                        new UserDto.Response(2L,
                                "Stub_Potato2",
                                "stub_email2@user.com",
                                1234567890),
//                        userMapper.userToUserResponseDto(new User()),
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
                                        fieldWithPath("data[].userInformation.reputation").type((JsonFieldType.NUMBER)).description("질문 유저 명성"),
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
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 질문 수"),
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
