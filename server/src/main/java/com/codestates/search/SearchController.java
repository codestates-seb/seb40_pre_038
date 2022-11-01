package com.codestates.search;

import com.codestates.dto.MultiResponseDto;
import com.codestates.question.Question;
import com.codestates.question.QuestionMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;
    private final QuestionMapper mapper;

    public SearchController(SearchService searchService, QuestionMapper mapper) {
        this.searchService = searchService;
        this.mapper = mapper;
    }

    @GetMapping("/{content}")
    public ResponseEntity getSearchResult(@PathVariable("content") @NotBlank String content,
                                          @Positive @RequestParam int page,
                                          @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        Page<Question> pageQuestions = searchService.findContent(content, page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponses(questions), pageQuestions),
                HttpStatus.OK);
    }
}
