package com.movie.demo.controller;

import com.movie.demo.MovieApplication;
import com.movie.demo.model.MovieDetails;
import com.movie.demo.repository.MovieDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieDetailsRepository movieDetailsRepository;

    @Test
    public void shouldReturn404ForUnknownURL() throws Exception {
        mvc.perform(get("/unhandledURL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void shouldReturn404ForMovieDetailsNotInRepo() throws Exception {
        given(movieDetailsRepository.getMovieDetails("5")).willReturn(null);

        mvc.perform(get("/movies/5/info")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void shouldReturnMovieDetailsAndComments() throws Exception {
        given(movieDetailsRepository.getMovieDetails("1")).willReturn(new MovieDetails("id", "title", "description"));

        mvc.perform(get("/movies/1/info")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(".id").value("id"))
                .andExpect(jsonPath(".title").value("title"))
                .andExpect(jsonPath(".description").value("description"));
    }

    @Test
    public void addMovieDetailsShouldReturn401WithoutRole() throws Exception {
        mvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void addMovieDetailsShouldReturn403WithoutAdminRole() throws Exception {
        mvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void addMovieDetailsShouldReturnOKForAdminRole() throws Exception {
        mvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "NOT_USER")
    public void addMovieCommentsShouldReturn403WithoutUserRole() throws Exception {
        mvc.perform(post("/movies/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void addMovieCommentsShouldReturnOKForUserRole() throws Exception {
        mvc.perform(post("/movies/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }
}