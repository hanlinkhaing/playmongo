package com.handev.playmongo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tomatoes {
    private int fresh;
    private int rotten;
    private Date lastUpdated;
    private View critic;
    private View viewer;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class View {
        private double rating;
        private int numReviews;
        private int meter;
    }
}
