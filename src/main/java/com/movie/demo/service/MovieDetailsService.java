package com.movie.demo.service;

import com.movie.demo.exceptions.ResourceNotFoundException;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.repository.MovieDetailsRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

import static com.movie.demo.config.CacheConfig.MOVIE_DETAILS_CACHE;

@Service
public class MovieDetailsService {
    private MovieDetailsRepository movieDetailsRepository;

    public MovieDetailsService(MovieDetailsRepository movieDetailsRepository) {
        this.movieDetailsRepository = movieDetailsRepository;
    }

    @Async
    @Cacheable(value = MOVIE_DETAILS_CACHE, key = "#id")
    @NotNull
    public CompletableFuture<MovieDetails> getMovieDetails(String id) {
        MovieDetails details = movieDetailsRepository.getMovieDetails(id);
        if (details == null) {
            throw new ResourceNotFoundException("Cannot find movie details with id " + id);
        }
        return CompletableFuture.completedFuture(details);
    }

    @Async
    @CachePut(value = MOVIE_DETAILS_CACHE, key = "#movieDetails.id")
    public void addMovieDetails(MovieDetails movieDetails) {
        // assume that id was generated before calling this method
        // implementation is not part of the task
    }
}
