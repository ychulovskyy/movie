package com.movie.demo.repository;

import com.movie.demo.model.MovieComment;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

// Stub should be used for tests only but used for prod to simplify implementation
@Repository
public class MovieCommentsRepositoryStub implements MovieCommentsRepository {
    private Map<String, List<MovieComment>> movieComments = new HashMap<>();

    {
        // demo data
        movieComments.put("1", Arrays.asList(
                new MovieComment("1", "user1", "First movie comment from first user"),
                new MovieComment("1", "user2", "First movie comment from second user"))
        );
        movieComments.put("2", Arrays.asList(
                new MovieComment("2", "user1", "Second movie comment from first user"))
        );
    }

    @Override
    public List<MovieComment> getMovieComments(String movieId) {
        if ("4".equals(movieId)) {
            throw new RuntimeException();
        }
        return movieComments.getOrDefault(movieId, emptyList());
    }
}
