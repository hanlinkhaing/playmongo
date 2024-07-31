package com.handev.playmongo.constants;

import lombok.Getter;

@Getter
public enum MovieSortField {
    IMDB_RATING("imdb.rating"),
    TOMATOES_RATING("tomatoes.viewer.rating");

    private final String value;

    MovieSortField(String value) {
        this.value = value;
    }


}
