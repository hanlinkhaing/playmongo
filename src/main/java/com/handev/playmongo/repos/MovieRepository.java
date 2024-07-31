package com.handev.playmongo.repos;

import com.handev.playmongo.dto.AggPageDto;
import com.handev.playmongo.dto.GenreWithTotalDto;
import com.handev.playmongo.dto.MovieSearchDto;
import com.handev.playmongo.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{

    Page<Movie> findAllBy(TextCriteria criteria, Pageable pageable);

    @Aggregation(pipeline = {
            "{ $unwind: { path: '$genres', preserveNullAndEmptyArrays: true } }",
            "{ $group: { _id: { genres: '$genres' }, total: { $sum: 1 } } }",
            "{ $project: { _id: 0, genre: { $cond: { if: { $eq: [ null, '$_id.genres' ] }, then: 'Other', else: '$_id.genres' } }, total: 1 } }",
            "{ $sort: { genre: 1 } }"
    })
    List<GenreWithTotalDto> getGenreWithTotal();

    @Aggregation(pipeline = {
            "{ $match: { $text: { $search: ?0 } } }",
            "{ $sort: { score: { $meta: 'textScore' } } }",
            """
              {
                $facet: {
                  "total": [
                    {
                      $count: "total"
                    }
                  ],
                  "content": [
                    {
                      $skip: ?1
                    },
                    {
                      $limit: ?2
                    },
                    {
                      $project: {
                        _id: 0,
                        id: { $toString: "$_id" },
                        title: 1,
                        plot: 1,
                        imdb: "$imdb.rating",
                        tomatoes: "$tomatoes.viewer.rating",
                        released: { $dateToString: { format: "%Y-%m-%d", date: "$released" } },
                        rated: 1,
                        poster: 1,
                        searchScore: { $meta: "textScore" }
                      }
                    }
                  ]
                }
              }
            """,
            """
              {
                $project: {
                  total: { $arrayElemAt: [ "$total.total", 0 ] },
                  content: 1
                }
              }
            """
    })
    AggPageDto<MovieSearchDto> getByFullTextSearch(String text, int skip, int limit);
}
