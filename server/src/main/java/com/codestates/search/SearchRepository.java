package com.codestates.search;

//import com.codestates.user.entity.user;
import com.codestates.question.Question;
import com.codestates.exception.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true,
            value = "select * from Question a where a.problem like %:body% or a.expect like %:body%")
    Page<Question> findAllByBody(String body, Pageable pageable);

    Page<Question> findAllByUser(User user, Pageable pageable);

    Page<Question> findAllByTagBodyContaining(String tagBody, Pageable pageable);
}
