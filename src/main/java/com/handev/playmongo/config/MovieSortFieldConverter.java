package com.handev.playmongo.config;

import com.handev.playmongo.constants.MovieSortField;
import org.springframework.core.convert.converter.Converter;

public class MovieSortFieldConverter implements Converter<String, MovieSortField> {

    @Override
    public MovieSortField convert(String value) {
        return switch (value) {
            case "imdb.rating" -> MovieSortField.IMDB_RATING;
            case "tomatoes.viewer.rating" -> MovieSortField.TOMATOES_RATING;
            default -> null;
        };
    }
}
