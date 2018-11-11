package com.movie.demo.repository;

import com.movie.demo.model.MovieDetails;

public interface MovieDetailsRepository {
    MovieDetails getMovieDetails(String id);
}
