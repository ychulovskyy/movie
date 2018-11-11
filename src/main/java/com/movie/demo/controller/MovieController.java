package com.movie.demo.controller;

import com.movie.demo.model.MovieComment;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.model.MovieInfo;
import com.movie.demo.service.MovieInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class MovieController {
    private MovieInfoService movieInfoService;

    public MovieController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping(path = "/movies/{id}/info", produces = APPLICATION_JSON_VALUE)
    public MovieInfo getMovieDetailsAndComments(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
        return movieInfoService.getMovieInfo(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/movies", consumes = APPLICATION_JSON_VALUE)
    public void addMovieDetails(@RequestBody MovieDetails movieDetails) {
        // Dummy endpoint for creating movieDetails details.
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(path = "/movies/{id}/comments", consumes = APPLICATION_JSON_VALUE)
    public void addMovieComment(@RequestBody MovieComment comment) {
        // Dummy endpoint for creating Comment
    }
}
