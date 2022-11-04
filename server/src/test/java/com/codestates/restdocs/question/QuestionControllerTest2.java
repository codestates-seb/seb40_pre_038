//package com.codestates.restdocs.question;
//
//import com.codestates.answer.controller.AnswerController;
//import com.codestates.answer.service.AnswerService;
//import com.codestates.auth.jwt.JwtTokenizer;
//import com.codestates.auth.utils.CustomAuthorityUtils;
//import com.codestates.question.*;
//import com.codestates.user.entity.User;
//import com.codestates.user.mapper.UserMapper;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codestates.util.ApiDocumentUtils.getDocumentRequest;
//import static com.codestates.util.ApiDocumentUtils.getDocumentResponse;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//public class QuestionControllerTest2 {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @MockBean
//    private AnswerController answerController;
//
//    @MockBean
//    private AnswerService answerService;
//
////    @MockBean
////    private QuestionService questionService;
////
////    @MockBean
////    private QuestionMapper questionMapper;
//
//    @MockBean
//    private UserMapper userMapper;
//
//    @MockBean
//    private JwtTokenizer jwtTokenizer;
//
//    @MockBean
//    private CustomAuthorityUtils customAuthorityUtils;
//
//    @Test
//    void postQuestionTest() throws Exception {
//
//        QuestionDto.Post post = new QuestionDto.Post(1L,
//                "title",
//                "problem12345123451234512345",
//                "expect12345123451234512345"
//                , "tag, body"
//                , List.of("tag", "body")
//        );
//
//        String content = gson.toJson(post);
//
//        QuestionDto.Response response =
//                new QuestionDto.Response(1L,
//                        userMapper.userToUserResponseDto(new User(1L,
//                                "Stub_Potato",
//                                "stub_email_@user.com",
//                                1234567890)),
//                        "title12345123451234512345",
//                        "problem12345123451234512345",
//                        "expect12345123451234512345",
//                        List.of("tag", "body"),
//                        0, 0,
//                        LocalDateTime.now(),
//                        LocalDateTime.now(),
//                        0,
//                        null,
//                        "ASDF"
//                );
//
////        given(questionMapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());
////
////        given(questionService.createQuestion(Mockito.any(Question.class), eq(1))).willReturn(new Question());
////
////        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/api/questions/add")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.userInformation.userId").value(post.getUserId()))
//                .andExpect(jsonPath("$.data.problem").value(post.getProblem()))
//                .andExpect(jsonPath("$.data.expect").value(post.getExpect()))
//                .andExpect(jsonPath("$.data.tagList[0:2]").value(new ArrayList<>(post.getTags())))
//                .andDo(document(
//                        "post-question",
//                        getDocumentRequest(),
//                        getDocumentResponse(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 번호"),
//                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
//                                        fieldWithPath("problem").type(JsonFieldType.STRING).description("문제"),
//                                        fieldWithPath("expect").type(JsonFieldType.STRING).description("기대"),
//                                        fieldWithPath("tagBody").type(JsonFieldType.STRING).description("태그 바디"),
//                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그 리스트")
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
//                                        fieldWithPath("data.questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
//                                        fieldWithPath("data.userInformation.userId").type((JsonFieldType.NUMBER)).description("유저 번호"),
//                                        fieldWithPath("data.userInformation.nickName").type((JsonFieldType.STRING)).description("유저 이름"),
//                                        fieldWithPath("data.userInformation.email").type((JsonFieldType.STRING)).description("유저 메일주소"),
//                                        fieldWithPath("data.userInformation.reputation").type((JsonFieldType.NUMBER)).description("유저 명성"),
//                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
//                                        fieldWithPath("data.problem").type(JsonFieldType.STRING).description("문제"),
//                                        fieldWithPath("data.expect").type(JsonFieldType.STRING).description("기대"),
//                                        fieldWithPath("data.tagList").type(JsonFieldType.ARRAY).description("태그 리스트"),
//                                        fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
//                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
//                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
//                                        fieldWithPath("data.commentsWithUser").type(JsonFieldType.ARRAY).description("댓글"),
//                                        fieldWithPath("data.actionStatus").type(JsonFieldType.STRING).description("질문 상태")
//                                )
//                        )
//                ));
//    }
//}
