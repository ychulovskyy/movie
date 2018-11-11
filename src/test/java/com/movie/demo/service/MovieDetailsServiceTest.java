package com.movie.demo.service;

import com.movie.demo.MovieApplication;
import com.movie.demo.exceptions.ResourceNotFoundException;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.repository.MovieDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
public class MovieDetailsServiceTest {
    private static final MovieDetails MOVIE_DETAILS = new MovieDetails("1", "title", "description");

    @Autowired
    private MovieDetailsService movieDetailsService;

    @MockBean
    private MovieDetailsRepository movieDetailsRepository;

    @Test(expected = ResourceNotFoundException.class)
    public void getMovieDetailsShouldThrowExceptionIfNoData() throws ExecutionException, InterruptedException {
        when(movieDetailsRepository.getMovieDetails("2")).thenReturn(null);

        movieDetailsService.getMovieDetails("2").get();
    }

    @Test
    public void getMovieDetailsShouldReturnData() throws ExecutionException, InterruptedException {
        when(movieDetailsRepository.getMovieDetails("1")).thenReturn(MOVIE_DETAILS);

        assertEquals(MOVIE_DETAILS, movieDetailsService.getMovieDetails("1").get());
    }
}