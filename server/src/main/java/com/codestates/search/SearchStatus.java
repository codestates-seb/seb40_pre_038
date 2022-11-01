package com.codestates.search;

import lombok.Getter;

public enum SearchStatus {
    STATUS_NEWEST(0, "최신순"),
    STATUS_SCORE(1, "투표순");

    @Getter
    private int statusNumber;

    @Getter
    private String statusDescription;

    SearchStatus(int statusNumber, String statusDescription) {
        this.statusNumber = statusNumber;
        this.statusDescription = statusDescription;
    }
}
