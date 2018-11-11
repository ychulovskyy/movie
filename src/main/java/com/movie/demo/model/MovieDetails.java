package com.movie.demo.model;

import java.util.Objects;

public class MovieDetails {
    private String id;
    private String title;
    private String description;

    public MovieDetails() {
    }

    public MovieDetails(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDetails movieDetails = (MovieDetails) o;
        return Objects.equals(id, movieDetails.id) &&
                Objects.equals(title, movieDetails.title) &&
                Objects.equals(description, movieDetails.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
