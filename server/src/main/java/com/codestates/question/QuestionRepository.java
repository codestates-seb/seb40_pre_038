package com.codestates.question;

import com.codestates.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, value = "select * from Tag_list a where a.tag_body = :tagBody")
    Page<Question>findQuestionsByTagBody(String tagBody, Pageable pageable);


}
