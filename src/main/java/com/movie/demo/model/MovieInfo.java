package com.movie.demo.model;

import java.util.List;
import java.util.Objects;

public class MovieInfo {
    private String id;
    private String title;
    private String description;
    private List<MovieComment> movieComments;

    public MovieInfo() {
    }

    public MovieInfo(MovieDetails movieDetails, List<MovieComment> movieComments) {
        this.id = movieDetails.getId();
        this.title = movieDetails.getTitle();
        this.description = movieDetails.getDescription();
        this.movieComments = movieComments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieComment> getMovieComments() {
        return movieComments;
    }

    public void setMovieComments(List<MovieComment> movieComments) {
        this.movieComments = movieComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieInfo movieInfo = (MovieInfo) o;
        return Objects.equals(id, movieInfo.id) &&
                Objects.equals(title, movieInfo.title) &&
                Objects.equals(description, movieInfo.description) &&
                Objects.equals(movieComments, movieInfo.movieComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, movieComments);
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", movieComments=" + movieComments +
                '}';
    }
}
