package com.codestates.search;

import com.codestates.question.Question;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class SearchSpecification {
    public static Specification<Question> containsTag(String tag) {
        return (root, query, criteriaBuilder) -> {
            Predicate tagForStartsWith = criteriaBuilder.like(root.get("tagBody"), tag + ",%");
            Predicate tagForContains = criteriaBuilder.like(root.get("tagBody"), "% " + tag + ",%");
            Predicate tagForEndsWith = criteriaBuilder.like(root.get("tagBody"), "% " + tag);

            return criteriaBuilder.or(tagForStartsWith, tagForContains, tagForEndsWith);
        };
    }

    public static Specification<Question> equalToUser(String userNickName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("user").get("nickName"), userNickName);
    }

    public static Specification<Question> containsTitleProblemExpect(String searchWord) {
        return (root, query, criteriaBuilder) -> {
            Predicate titleContains = criteriaBuilder.like(root.get("title"), "%" + searchWord + "%");
            Predicate problemContains = criteriaBuilder.like(root.get("problem"), "%" + searchWord + "%");
            Predicate expectContains = criteriaBuilder.like(root.get("expect"), "%" + searchWord + "%");

            return criteriaBuilder.or(titleContains, problemContains, expectContains);
        };
    }
}
