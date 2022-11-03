package com.codestates.search;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class SearchDto {
    private String content;
}
