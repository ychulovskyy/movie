package com.movie.demo.service;

import com.movie.demo.exceptions.ResourceNotFoundException;
import com.movie.demo.model.MovieComment;
import com.movie.demo.repository.MovieCommentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.movie.demo.config.CacheConfig.MOVIE_COMMENTS_CACHE;

@Service
public class MovieCommentsService {
    private Logger logger = LoggerFactory.getLogger(MovieCommentsService.class);
    private CacheManager cacheManager;
    private MovieCommentsRepository movieCommentsRepository;


    public MovieCommentsService(CacheManager cacheManager, MovieCommentsRepository movieCommentsRepository) {
        this.cacheManager = cacheManager;
        this.movieCommentsRepository = movieCommentsRepository;
    }

    @Async
    public CompletableFuture<List<MovieComment>> getMovieComments(String movieId) {
        try {
            return CompletableFuture.completedFuture(movieCommentsRepository.getMovieComments(movieId));
        } catch (RuntimeException ex) {
            logger.warn("Cannot get info from the movie comment service. Will return cached value", ex);

            // Comments service responses should also be cached but only as fallback.
            // When the backend service is down, comments should be taken from cache.
            return getCachedValueOrException(movieId);
        }
    }

    private CompletableFuture<List<MovieComment>> getCachedValueOrException(String movieId) {
        List<MovieComment> comments = (List<MovieComment>) cacheManager.getCache(MOVIE_COMMENTS_CACHE).get(movieId).get();
        if (comments != null) {
            return CompletableFuture.completedFuture(comments);
        }
        throw new ResourceNotFoundException("Cannot find movie comments for id " + movieId);
    }

    @Async
    @CachePut(value = MOVIE_COMMENTS_CACHE, key = "#comment.movieId")
    public void addMovieComment(MovieComment comment) {
        // assume that id was generated before calling this method
        // TODO: implement it
    }
}
