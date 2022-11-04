package com.codestates.search;

//import com.codestates.user.entity.user;
import com.codestates.question.Question;
import com.codestates.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<Question, Long> {

    /*@Query(nativeQuery = true,
            value = "select * from Answer a where a.status > 0 and a.question_id = :questionId"
                    + " order by status asc, vote desc, answer_id desc"
    )*/
    Page<Question> findAllByTitleContainingOrProblemContainingOrExpectContaining(String title, String problem, String expect, Pageable pageable);

    Page<Question> findAllByUser(User user, Pageable pageable);

    Page<Question> findAllByTagList(String tagBody, Pageable pageable);
}
