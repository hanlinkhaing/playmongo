package com.handev.playmongo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "movies")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String id;

    @TextIndexed
    private String title;

    private String plot;

    private List<String> genres;
    private int runtime;
    private List<String> cast;
    private String poster;

    @TextIndexed
    @Field("fullplot")
    private String fullPlot;

    private Date released;
    private List<String> directors;
    private String rated;
    private Award awards;
    private Date lastUpdated;
    private int year;
    private IMDB imdb;
    private Tomatoes tomatoes;
    private List<String> countries;
    private String type;

    @Field("num_mflix_comments")
    private int numMFlixComments;
}
