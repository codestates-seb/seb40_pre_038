package com.codestates.restdocs.question;

import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.answer.service.AnswerService;
import com.codestates.question.*;
import com.codestates.user.entity.User;
import com.codestates.vote.QuestionVote.QuestionVoteService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.codestates.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.util.ApiDocumentUtils.getDocumentResponse;
//import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "asdf")
@AutoConfigureRestDocs
public class QuestionControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionVoteService questionVoteService;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper answerMapper;

    @MockBean
    private QuestionMapper mapper;

    @Autowired
    private Gson gson;





    @Test
    public void postQuestionTest() throws Exception {

        User user = new User("testuser", "testuser@test.test", "1234567890");
        user.setUserId(1L);
        user.setPassword("1234567890");
        user.setUserStatus(User.UserStatus.USER_ACTIVE);


        // given
        QuestionDto.Post post =
                new QuestionDto.Post(1L,
                "title",
                "problem12345123451234512345",
                "expect12345123451234512345"
                , "tag, body"
                ,List.of("tag","body"));

        String content = gson.toJson(post);

        QuestionDto.Response responseDto =
                new QuestionDto.Response(1L,
                        null,
                        "title12345123451234512345",
                        "problem12345123451234512345",
                        "expect12345123451234512345",
                        List.of("tag", "body"),
                        0, 0,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        null
                );



        given(mapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class), eq(1))).willReturn(new Question());

        given(mapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(responseDto);

//        System.out.println(responseDto.getProblem());

        Question question = new Question();

        QuestionDto.Response a = mapper.questionToQuestionResponse(question);

//        System.out.println(mapper.questionToQuestionResponse(question).getProblem());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/api/questions/add")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(content)
                );

        System.out.println(actions.andDo(System.out::println));

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.problem").value(post.getProblem()))
                .andExpect(jsonPath("$.data.expect").value(post.getExpect()))
                .andExpect(jsonPath("$.data.tagBody").value(post.getTagBody()))
                .andExpect(jsonPath("$.data.tags").value(post.getTags()))
//                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
//                .andExpect(jsonPath("$.data.problem").value(post.getProblem()))
//                .andExpect(jsonPath("$.data.expect").value(post.getExpect()))
//                .andExpect(jsonPath("$.data.tagBody").value(post.getTagBody()))
//                .andExpect(jsonPath("$.data.tags").value(post.getTags()))
                .andDo(document(
                        "post-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("tagBody").type(JsonFieldType.STRING).description("태그 바디")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.problem").type(JsonFieldType.STRING).description("문제"),
                                        fieldWithPath("data.expect").type(JsonFieldType.STRING).description("기대"),
                                        fieldWithPath("data.tagBody").type(JsonFieldType.STRING).description("태그 바디"),
                                        fieldWithPath("data.tags").type(JsonFieldType.OBJECT).description("태그 리스트")
                                )
                        )
                ));
    }
}
