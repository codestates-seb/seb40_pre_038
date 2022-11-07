package com.codestates.search;

import com.codestates.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SearchRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

}
