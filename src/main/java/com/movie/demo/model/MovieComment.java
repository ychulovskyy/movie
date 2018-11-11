package com.movie.demo.model;

import java.util.Objects;

public class MovieComment {
    private String movieId;
    private String username;
    private String message;

    public MovieComment() {
    }

    public MovieComment(String movieId, String username, String message) {
        this.movieId = movieId;
        this.username = username;
        this.message = message;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieComment that = (MovieComment) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, username, message);
    }

    @Override
    public String toString() {
        return "MovieComment{" +
                "movieId='" + movieId + '\'' +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
