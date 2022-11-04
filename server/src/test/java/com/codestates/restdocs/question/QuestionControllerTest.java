//package com.codestates.restdocs.question;
//
//import com.codestates.answer.controller.AnswerController;
//import com.codestates.answer.service.AnswerService;
//import com.codestates.auth.jwt.JwtTokenizer;
//import com.codestates.auth.utils.CustomAuthorityUtils;
//import com.codestates.question.Question;
//import com.codestates.question.QuestionDto;
//import com.codestates.question.QuestionMapper;
//import com.codestates.question.QuestionService;
//import com.codestates.user.entity.User;
//import com.codestates.user.mapper.UserMapper;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@WebMvcTest(QuestionController.class)
//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
//public class QuestionControllerTest {
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
//    @MockBean
//    private UserMapper userMapper;
//
////    @MockBean
////    private QuestionMapper questionMapper;
////
////    @MockBean
////    private QuestionService questionService;
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
//
//        QuestionDto.Post post = new QuestionDto.Post(1L,
//                "title",
//                "problem12345123451234512345",
//                "expect12345123451234512345"
//                , "tag, body"
//                , List.of("tag", "body")
//        );
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
//        String content = gson.toJson(post);
//
////        System.out.println("------------------------------------------------------");
////        System.out.println(response.getProblem());
////        System.out.println("------------------------------------------------------");
////
////        given(questionMapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());
////
////        given(questionService.createQuestion(Mockito.any(Question.class), eq(1))).willReturn(new Question());
////
////        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(
////                response.builder()
////                        .questionId(response.getQuestionId())
////                        .userInformation(response.getUserInformation())
////                        .title(response.getTitle())
////                        .problem(response.getProblem())
////                        .expect(response.getExpect())
////                        .view(response.getView())
////                        .vote(response.getVote())
////                        .tagList(response.getTagList())
////                        .createdAt(response.getCreatedAt())
////                        .modifiedAt(response.getModifiedAt())
////                        .answerCount(0)
////                        .commentsWithUser(null)
////                        .actionStatus(response.getActionStatus())
////                        .build());
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/api/questions/add")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        MvcResult result = actions
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.userInformation.userId").value(post.getUserId()))
//                .andExpect(jsonPath("$.data.problem").value(post.getProblem()))
//                .andExpect(jsonPath("$.data.expect").value(post.getExpect()))
//                .andExpect(jsonPath("$.data.tagList[0:2]").value(new ArrayList<>(post.getTags())))
//                .andReturn();
//    }
//}
