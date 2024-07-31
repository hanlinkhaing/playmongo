package com.handev.playmongo.services;

import com.handev.playmongo.constants.MovieSortField;
import com.handev.playmongo.constants.SortOrder;
import com.handev.playmongo.dto.MovieSearchDto;
import com.handev.playmongo.models.Movie;
import com.handev.playmongo.repos.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    public List<Movie > findAll() {
        return movieRepository.findAll(Pageable.ofSize(10).withPage(0)).getContent();
    }

    public Page<Movie> findByFullTextSearch(String text,
                                                     MovieSortField sortBy,
                                                     SortOrder sortOrder,
                                                     int page,
                                                     int size) {
        Sort sort = Sort.by(sortOrder.getValue(), sortBy.getValue());
        TextCriteria criteria = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny(text);

        return movieRepository.findAllByOrderByScoreDesc(criteria, PageRequest.of(page - 1, size, sort));
    }

    public List<MovieSearchDto> findWithTemplate(String text) {
        Query query = new TextQuery(TextCriteria.forDefaultLanguage()
                .matchingAny(text))
                .sortByScore()
                .with(PageRequest.of(0, 10));
        return mongoTemplate.find(query, MovieSearchDto.class, "movies");
    }
}
