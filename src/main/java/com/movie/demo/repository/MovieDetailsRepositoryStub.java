package com.movie.demo.repository;

import com.movie.demo.model.MovieDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// Stub should be used for tests only but used for prod to simplify implementation
@Repository
public class MovieDetailsRepositoryStub implements MovieDetailsRepository {
    private Map<String, MovieDetails> movies = new HashMap<>();

    {
        movies.put("1", new MovieDetails("1", "First movie title", "First movie description"));
        movies.put("2", new MovieDetails("2", "Second movie title", "Second movie description"));
        movies.put("3", new MovieDetails("3", "Third movie title", "Third movie description"));
        movies.put("4", new MovieDetails("4", "4-th movie title", "4-th movie description"));
    }

    @Override
    public MovieDetails getMovieDetails(String id) {
        // Artificial delay of 1s for demonstration purposes
        // First request to MovieDetailsService.getMovieDetails for specific id will be slow,
        // following requests will be fast because of caching
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return movies.get(id);
    }
}
