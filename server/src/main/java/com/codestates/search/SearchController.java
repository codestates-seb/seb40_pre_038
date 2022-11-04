package com.codestates.search;

import com.codestates.dto.MultiResponseDto;
import com.codestates.question.Question;
import com.codestates.question.QuestionMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
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

    @GetMapping(value = {"", "/{tab}"})
    public ResponseEntity getSearchResult(@PathVariable(required = false, value = "tab") String tab,
                                          @Valid @RequestBody SearchDto searchDto,
                                          @Positive @RequestParam(required = false, defaultValue = "1") int page,
                                          @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        if(tab == null) tab = "newest";
        if(!tab.equals("newest") && !tab.equals("score"))
            return new ResponseEntity<>("잘못된 tab 요청입니다", HttpStatus.BAD_REQUEST);
        Page<Question> pageQuestions = searchService.findContent(searchDto.getContent(), tab,page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponsesTopAll(questions), pageQuestions),
                HttpStatus.OK);
    }
}
