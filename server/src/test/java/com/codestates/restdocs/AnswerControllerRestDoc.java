package com.codestates.restdocs;

import com.codestates.answer.controller.AnswerController;
import com.codestates.answer.dto.AnswerPatchDto;
import com.codestates.answer.dto.AnswerPostDto;
import com.codestates.answer.dto.AnswerResponseDto;
import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
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
import org.springframework.transaction.annotation.Transactional;

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

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AnswerControllerRestDoc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper answerMapper;

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
    }

    @Test
    void postAnswerTest() throws Exception {

        long questionId = 1;

        AnswerPostDto post = new AnswerPostDto(
                "Answer should have more than 20 characters"
        );

        String content = gson.toJson(post);

        AnswerResponseDto response =
                new AnswerResponseDto(1L,
                        userMapper.userToUserResponseDto(new User()),
                        Answer.AnswerStatus.ANSWER_NORMAL,
                        1L,
                        "Answer should have more than 20 characters",
                        0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null,
                        "answered"
                );


        given(answerMapper.answerPostDtoToAnswer(anyLong(), Mockito.any(AnswerPostDto.class))).willReturn(new Answer());

        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);


        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/questions/{questionId}/answers/add", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.body").value(post.getBody()))
                .andDo(document(
                        "post-answer",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("questionId").description("질문 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type((JsonFieldType.NUMBER)).description("답변 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("답변 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("답변 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("답변 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("답변 유저 명성"),
                                        fieldWithPath("data.answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));
    }

    @Test
    void patchAnswerTest() throws Exception {

        long answerId = 1;

        AnswerPatchDto patch = new AnswerPatchDto(
                1L,
                "Changed answer should have more than 20 characters"
        );

        String content = gson.toJson(patch);

        AnswerResponseDto response =
                new AnswerResponseDto(1L,
                        userMapper.userToUserResponseDto(new User()),
                        Answer.AnswerStatus.ANSWER_NORMAL,
                        1L,
                        "Changed answer should have more than 20 characters",
                        0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null,
                        "modified"
                );

        given(answerMapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class))).willReturn(new Answer());

        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(answerMapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/api/answers/{answerId}/edit", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.data.body").value(patch.getBody()))
                .andDo(document(
                        "patch-answer",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("answerId").description("답변 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("질문 번호"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("수정된 답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.answerId").type((JsonFieldType.NUMBER)).description("답변 번호"),
                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("답변 유저 번호"),
                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("답변 유저 이름"),
                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("답변 유저 이메일 주소"),
                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("답변 유저 명성"),
                                        fieldWithPath("data.answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
                                        fieldWithPath("data.body").type(JsonFieldType.STRING).description("수정된 답변 내용"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));
    }

    @Test
    void getAnswersTest() throws Exception {

        long questionId = 1;

        List<AnswerResponseDto> responses = List.of(

                new AnswerResponseDto(1L,
                        userMapper.userToUserResponseDto(new User()),
                        Answer.AnswerStatus.ANSWER_NORMAL,
                        1L,
                        "Answer1 should have more than 20 characters",
                        0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null,
                        "answered"
                ),

                new AnswerResponseDto(2L,
                        userMapper.userToUserResponseDto(new User()),
                        Answer.AnswerStatus.ANSWER_NORMAL,
                        2L,
                        "Answer2 should have more than 20 characters",
                        0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        null,
                        "modified"
                )
        );

        List<Answer> answerPage = new ArrayList<>();

        given(answerService.findAnswers(Mockito.anyLong())).willReturn(answerPage);

        given(answerMapper.answersToAnswerResponseDtos(Mockito.anyList())).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/questions/{questionId}/answers", questionId)
                                .param("page", "1")
                                .param("size", "15")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions.andExpect(status().isOk())
                .andDo(document("get-answers",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].answerId").type((JsonFieldType.NUMBER)).description("답변 번호"),
                                        fieldWithPath("data[].userInformation.userId").type((JsonFieldType.NUMBER)).description("답변 유저 번호"),
                                        fieldWithPath("data[].userInformation.nickName").type((JsonFieldType.STRING)).description("답변 유저 이름"),
                                        fieldWithPath("data[].userInformation.email").type((JsonFieldType.STRING)).description("답변 유저 이메일 주소"),
                                        fieldWithPath("data[].userInformation.reputation").type((JsonFieldType.NUMBER)).description("답변 유저 명성"),
                                        fieldWithPath("data[].answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 번호"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data[].vote").type(JsonFieldType.NUMBER).description("투표"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
                                        fieldWithPath("data[].commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
                                        fieldWithPath("data[].actionStatus").type(JsonFieldType.STRING).description("질문 상태")
                                )
                        )
                ));

    }

    @Test
    void deleteAnswerTest() throws Exception {

        long answerId = 1L;

        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        mockMvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/answers/{answerId}/delete", answerId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answer",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                pathParameters(
                                        parameterWithName("answerId").description("답변 번호")
                                )
                        )
                );
    }
}
