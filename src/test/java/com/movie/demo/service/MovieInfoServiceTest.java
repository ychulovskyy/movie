package com.movie.demo.service;

import com.movie.demo.MovieApplication;
import com.movie.demo.model.MovieComment;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.model.MovieInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
public class MovieInfoServiceTest {
    @MockBean
    private MovieDetailsService movieDetailsService;

    @MockBean
    private MovieCommentsService movieCommentsService;

    @Autowired
    private MovieInfoService movieInfoService;

    @Test
    public void getMovieInfoShouldMergeDataFrom2Services() throws ExecutionException, InterruptedException {
        MovieDetails movieDetails = new MovieDetails("1", "title", "description");
        when(movieDetailsService.getMovieDetails("1")).thenReturn(CompletableFuture.completedFuture(movieDetails));

        List<MovieComment> movieComments = Arrays.asList(new MovieComment("1", "username", "message"));
        when(movieCommentsService.getMovieComments("1")).thenReturn(CompletableFuture.completedFuture(movieComments));

        assertEquals(new MovieInfo(movieDetails, movieComments), movieInfoService.getMovieInfo("1"));
    }
}