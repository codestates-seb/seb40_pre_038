package com.codestates.restdocs;

import com.codestates.answer.controller.AnswerController;
import com.codestates.answer.service.AnswerService;
import com.codestates.auth.jwt.JwtTokenizer;
import com.codestates.auth.utils.CustomAuthorityUtils;
import com.codestates.question.Question;
import com.codestates.question.QuestionDto;
import com.codestates.question.QuestionMapper;
import com.codestates.question.QuestionService;
import com.codestates.user.assembler.UserAssembler;
import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import com.codestates.user.mapper.UserMapper;
import com.codestates.user.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilderFactory;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class UserControllerRestDoc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerController answerController;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

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
    void getUserTest() throws Exception {

        long userId = 1;

        UserDto.Response user = new UserDto.Response(
                1L,
                "Stub_Potato",
                "stub_email@user.com",
                1234567890
        );

        EntityModel response = EntityModel.of(user,
                Arrays.asList(Link.of("http://localhost:8080/api/users/1", "self"),
                        Link.of("http://localhost:8080/api/users", "users")));

        given(userService.findOne(anyLong())).willReturn(new User());

        given(userAssembler.toModel(Mockito.any(UserDto.Response.class))).willReturn(response);


        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/users/{userId}", userId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-user",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("userId").description("회원 번호")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("userId").type((JsonFieldType.NUMBER)).description("유저 번호"),
                                        fieldWithPath("nickName").type((JsonFieldType.STRING)).description("유저 이름"),
                                        fieldWithPath("email").type((JsonFieldType.STRING)).description("유저 이메일 주소"),
                                        fieldWithPath("reputation").type((JsonFieldType.NUMBER)).description("유저 명성"),
                                        fieldWithPath("_links").type(JsonFieldType.OBJECT).description("링크"),
                                        fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("유저 정보 링크"),
                                        fieldWithPath("_links.users.href").type(JsonFieldType.STRING).description("전체 유저 정보 링크")
                                )
                        )
                ));
    }

//    @Test
//    void getUsersTest() throws Exception {
//
//        UserDto.Response user1 = new UserDto.Response(
//                1L,
//                "Stub_Potato1",
//                "stub_email1@user.com",
//                1234567890
//        );
//
//        EntityModel userEntityModel1 = EntityModel.of(user1
////                ,Arrays.asList(Link.of("http://localhost:8080/api/users/1", "self"),
////                        Link.of("http://localhost:8080/api/users", "users"))
//        );
//
//        UserDto.Response user2 = new UserDto.Response(
//                2L,
//                "Stub_Potato2",
//                "stub_email2@user.com",
//                1234567890
//        );
//
//        EntityModel userEntityModel2 = EntityModel.of(user2
//                ,Arrays.asList(Link.of("http://localhost:8080/api/users/2", "self"),
//                        Link.of("http://localhost:8080/api/users", "users"))
//        );
//
//        List<EntityModel<UserDto.Response>> userList = List.of(userEntityModel1, userEntityModel2);
//
////        CollectionModel responses = CollectionModel.of(userList,
////                Arrays.asList(Link.of("http://localhost:8080", "self"))
////                );
//
//
//        CollectionModel responses = CollectionModel.of(userList
//                ,Arrays.asList(Link.of("http://localhost:8080", "self"))
//        );
//
//        given(userService.findAll().stream()
//                .map(userMapper::userToUserResponseDto)
//                .map(userAssembler::toModel)
//                .collect(Collectors.toList())).willReturn(new ArrayList<>());
//
//        System.out.println(anyList().getClass());
////        System.out.println(any.getClass());
//
//
//
//        given(CollectionModel.of(Mockito.any(ArrayList.class),
//                Mockito.any(Link.class)))
//                .willReturn(responses);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.get("/api/users")
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        actions.andExpect(status().isOk())
//                .andDo(document("get-questions",
//                        getDocumentRequest(),
//                        getDocumentResponse(),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data.").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                        fieldWithPath("data[].questionId").type((JsonFieldType.NUMBER)).description("질문 번호"),
//                                        fieldWithPath("data[].userInformation.userId").type((JsonFieldType.NUMBER)).description("질문 유저 번호"),
//                                        fieldWithPath("data[].userInformation.nickName").type((JsonFieldType.STRING)).description("질문 유저 이름"),
//                                        fieldWithPath("data[].userInformation.email").type((JsonFieldType.STRING)).description("질문 유저 이메일 주소"),
//                                        fieldWithPath("data[].userInformation.reputation").type((JsonFieldType.NUMBER)).description("질문 유저 명성"),
//                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("제목"),
//                                        fieldWithPath("data[].problem").type(JsonFieldType.STRING).description("문제"),
//                                        fieldWithPath("data[].expect").type(JsonFieldType.STRING).description("기대"),
//                                        fieldWithPath("data[].tagList").type(JsonFieldType.ARRAY).description("태그 리스트"),
//                                        fieldWithPath("data[].view").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("data[].vote").type(JsonFieldType.NUMBER).description("투표"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 날짜"),
//                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("마지막 수정 날짜"),
//                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
//                                        fieldWithPath("data[].commentsWithUser").type(JsonFieldType.NULL).description("댓글"),
//                                        fieldWithPath("data[].actionStatus").type(JsonFieldType.STRING).description("질문 상태"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
//                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 질문 수"),
//                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
//                                )
//                        )
//                ));
//
//    }

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
