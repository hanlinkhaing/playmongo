package com.handev.playmongo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieSearchDto {
    private String id;
    private String title;
    private String plot;
    private String score;
    private String released;
    private String rated;
    private double imdb;
    private double tomatoes;
    private String poster;
    private double searchScore;
}
