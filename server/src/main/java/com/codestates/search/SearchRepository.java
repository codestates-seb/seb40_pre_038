package com.codestates.search;

import com.codestates.user.entity.User;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByBodyContaining(String body, Pageable pageable);

    Page<Question> findAllByUser(User user, Pageable pageable);
}
