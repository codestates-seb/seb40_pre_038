//package com.codestates.stub;
//
//import com.codestates.answer.entity.Answer;
//import com.codestates.answer.repository.AnswerRepository;
//import com.codestates.answer.service.AnswerService;
//import com.codestates.comment.CommentRepository;
//import com.codestates.comment.entity.Comment;
//import com.codestates.question.Question;
//import com.codestates.question.QuestionRepository;
//import com.codestates.question.QuestionService;
//import com.codestates.user.entity.User;
//import com.codestates.user.repository.UserRepository;
//import com.codestates.user.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codestates.answer.entity.Answer.AnswerStatus.ANSWER_NORMAL;
//import static com.codestates.comment.entity.CommentType.ANSWER;
//import static com.codestates.comment.entity.CommentType.QUESTION;
//
//@Configuration
//public class Stub_Test {
//
//    private static final Logger log = LoggerFactory.getLogger(Stub_Test.class);
//
//    @Bean
//    CommandLineRunner QuestionInit(QuestionRepository questionRepository, QuestionService questionService,
//                                   UserRepository userRepository, UserService userService,
//                                   AnswerRepository answerRepository, AnswerService answerService,
//                                   CommentRepository commentRepository) {
//
//                userRepository.save(new User("Stub_Potato",
//                "stub_email_@user.com",
//                "stub_password_1234",
//                1234567890));
//
//        return null;

//        return
//                args -> {
//
//            for (long i = 1; i <= 35; i++) {
//
//                long rand = (long) (Math.random() * 100) + 1; // 스텁 유저를 위한 랜덤 넘버
//
//                List<String> tagList = new ArrayList<>(); // 태그 리스트 생성
//
//                tagList.add("tagbody" + i);
//                tagList.add("javatagbody" + i);
//                tagList.add("javascripttagbody" + i);
//
//
//                log.info("QUESTION STUB " + // 질문 스텁 생성
//                        questionRepository.save(new Question(
//                                "Title " + i,
//                                "Problem " + i + " JavaProblem " + i + "JavaScriptProblem " + i + " SpringProblem " + i + " ReactProblem " + i,
//                                "Expect " + i + " JavaExpect " + i + " JavaScriptExpect " + i + " SpringExpect " + i + " ReactExpect " + i,
//
//                                "tagbody" + i + ", javatagbody" + i + ", javascripttagbody" + i,
//
//                                tagList,
//                                (int) (Math.random() * 1000),
//                                (int) (Math.random() * 1000),
//                                userService.findOne(rand),
//                                null,
//                                null,
//                                LocalDateTime.now(),
//                                null)));
//
//                log.info("ANSWER STUB " + // 답변 스텁 생성
//                        answerRepository.save(new Answer(
//                                ANSWER_NORMAL,
//                                userService.findOne(rand),
//                                questionService.findQuestion(i),
//                                "AnswerBody " + i + " JavaAnswer " + i + "JavaScriptAnswer " + i + " SpringAnswer " + i + " ReactAnswer " + i,
//                                null)));
//
//                log.info("QUESTION_COMMENT STUB " + // 질문_댓글 스텁 생성
//                        commentRepository.save(new Comment(
//                                QUESTION,
//                                userService.findOne(rand),
//                                questionService.findQuestion(i),
//                                "QuestionCommentBody " + i + " JavaQuestionComment " + i + " JavaScriptQuestionComment " + i + " SpringQuestionComment " + i + " ReactQuestionComment " + i)));
//
//                log.info("ANSWER_COMMENT STUB " + // 답변_댓글 스텁 생성
//                        commentRepository.save(new Comment(
//                                ANSWER,
//                                userService.findOne(rand),
//                                answerService.findAnswer(i),
//                                "AnswerCommentBody " + i + " JavaAnswerComment " + i + " JavaScriptAnswerComment " + i + " SpringAnswerComment " + i + " ReactAnswerComment " + i)));
//            }
//
//            for (long i = 36; i <= 38; i++) {
//
//                long rand = (long) (Math.random() * 100) + 1; // 스텁 유저를 위한 랜덤 넘버
//
//                List<String> tagList = new ArrayList<>(); // 태그 리스트 생성
//
//                tagList.add("tagbody" + i);
//                tagList.add("javatagbody" + i);
//                tagList.add("javascripttagbody" + i);
//
//
//                log.info("QUESTION STUB " + // 질문 스텁 생성
//                        questionRepository.save(new Question(
//                                "Title " + i,
//                                "Problem " + i + " JavaProblem " + i + "JavaScriptProblem " + i + " SpringProblem " + i + " ReactProblem " + i,
//                                "Expect " + i + " JavaExpect " + i + " JavaScriptExpect " + i + " SpringExpect " + i + " ReactExpect " + i,
//
//                                "tagbody" + i + ", javatagbody" + i + ", javascripttagbody" + i,
//
//                                tagList,
//                                (int) (Math.random() * 1000),
//                                (int) (Math.random() * 1000),
//                                userService.findOne(rand),
//                                null,
//                                null,
//                                LocalDateTime.now(),
//                                null)));
//            }
//        };
//    }
//}
