package com.movie.demo.repository;

import com.movie.demo.model.MovieComment;

import java.util.List;

public interface MovieCommentsRepository {
    List<MovieComment> getMovieComments(String movieId);
}
