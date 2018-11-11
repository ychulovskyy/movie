package com.movie.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String MOVIE_COMMENTS_CACHE = "MovieCommentsCache";
    public static final String MOVIE_DETAILS_CACHE = "MovieDetailsCache";
}
