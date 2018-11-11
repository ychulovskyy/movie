package com.movie.demo.service;

import com.movie.demo.model.MovieInfo;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MovieInfoService {
    private MovieDetailsService movieDetailsService;
    private MovieCommentsService movieCommentsService;

    public MovieInfoService(MovieDetailsService movieDetailsService, MovieCommentsService movieCommentsService) {
        this.movieDetailsService = movieDetailsService;
        this.movieCommentsService = movieCommentsService;
    }

    public MovieInfo getMovieInfo(String id) throws InterruptedException, ExecutionException {
        return movieDetailsService.getMovieDetails(id)
                .thenCombine(movieCommentsService.getMovieComments(id), MovieInfo::new)
                .get();
    }
}
