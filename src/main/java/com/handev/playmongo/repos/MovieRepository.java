package com.handev.playmongo.repos;

import com.handev.playmongo.dto.MovieSearchDto;
import com.handev.playmongo.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{

    Page<Movie> findAllByOrderByScoreDesc(TextCriteria criteria, Pageable pageable);
}
