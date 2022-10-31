//package com.codestates.tag;
//
//import com.codestates.question.Question;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Set;
//
//@Repository
//public interface TagRepository extends JpaRepository<Tag, Long> {
//
////    @Query(value = "select * from tag where question_id = :questionId",nativeQuery = true)
////    List<Tag> findAllByQuestionId(@Param("questionId") long questionId);
////    List<Tag> findAllByQuestionAndTagStatus(Question question, Tag.TagStatus tagStatus);
//}
