package com.movie.demo.service;

import com.movie.demo.MovieApplication;
import com.movie.demo.exceptions.ResourceNotFoundException;
import com.movie.demo.model.MovieComment;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.repository.MovieCommentsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.movie.demo.config.CacheConfig.MOVIE_COMMENTS_CACHE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
public class MovieCommentsServiceTest {
    private final static MovieComment MOVIE_COMMENT = new MovieComment("1", "username", "message");
    private final static MovieComment MOVIE_COMMENT2 = new MovieComment("2", "username", "message");

    @Autowired
    private MovieCommentsService movieCommentsService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private MovieCommentsRepository movieCommentsRepository;

    @Test
    public void getMovieDetailsShouldReturnData() throws ExecutionException, InterruptedException {
        when(movieCommentsRepository.getMovieComments("1")).thenReturn(Arrays.asList(MOVIE_COMMENT));

        assertEquals(Arrays.asList(MOVIE_COMMENT), movieCommentsService.getMovieComments("1").get());
    }

    @Test
    public void getMovieDetailsShouldFallbackToCache() throws ExecutionException, InterruptedException {
        when(movieCommentsRepository.getMovieComments("2")).thenThrow(new RuntimeException());
        cacheManager.getCache(MOVIE_COMMENTS_CACHE).put("2", Arrays.asList(MOVIE_COMMENT2));

        assertEquals(Arrays.asList(MOVIE_COMMENT2), movieCommentsService.getMovieComments("2").get());
    }

}