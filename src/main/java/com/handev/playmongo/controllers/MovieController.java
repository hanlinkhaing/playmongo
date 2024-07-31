package com.handev.playmongo.controllers;

import com.handev.playmongo.constants.MovieSortField;
import com.handev.playmongo.constants.SortOrder;
import com.handev.playmongo.dto.MovieSearchDto;
import com.handev.playmongo.dto.Response;
import com.handev.playmongo.models.Movie;
import com.handev.playmongo.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @RequestMapping
    public ResponseEntity<Response<List<Movie>>> findAll() {
        return Response.<List<Movie>>builder()
                .status(HttpStatus.OK)
                .message("Movies retrieved successfully")
                .data(movieService.findAll())
                .build();
    }

    @RequestMapping("/full-text-search")
    public ResponseEntity<Response<Page<Movie>>> findByFullTextSearch(
            @RequestParam String text,
            @RequestParam MovieSortField sortBy,
            @RequestParam SortOrder sortOrder,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return Response.<Page<Movie>>builder()
                .status(HttpStatus.OK)
                .message("Movie retrieved successfully")
                .data(movieService.findByFullTextSearch(text, sortBy, sortOrder, page, size))
                .build();
    }

    @RequestMapping("/full-text-search-with-template")
    public ResponseEntity<Response<List<MovieSearchDto>>> findWithTemplate(
            @RequestParam String text
    ) {
        return Response.<List<MovieSearchDto>>builder()
                .status(HttpStatus.OK)
                .message("Movie retrieved successfully")
                .data(movieService.findWithTemplate(text))
                .build();
    }
}
