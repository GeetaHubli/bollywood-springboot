package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value={"/sql/seed.sql"})
public class MovieServiceTest {
    @Autowired
    private  MovieService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertTrue(after.getId() > 0);
        assertEquals(2, after.getId());
        assertEquals(0,after.getVersion());
        assertEquals("The Matrix", after.getTitle());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotCreateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
        assertTrue(after.getId() > 0);
        assertEquals(2, after.getId());
    }

    @Test
    public void shouldFindMovieById() throws Exception {
        Movie after = this.service.findById(1);
        assertNotNull(after);
        assertEquals(1, after.getId());
        assertEquals("Ring",after.getTitle());
    }

    @Test
    public void shouldFindMovieByBadId() throws Exception {
        Movie after = this.service.findById(10);
        assertNull(after);
    }

    @Test
    public void shouldReturnAllMovies() throws Exception {
        ArrayList<Movie> movies = (ArrayList<Movie>) this.service.findAll();
        assertNotEquals(0,movies.size());
    }

    @Test
    public void shouldFindMovieByTitle() throws Exception {
        Movie after = this.service.findByTitle("Ring");
        assertNotNull(after);
        assertEquals(1, after.getId());
        assertEquals("Ring",after.getTitle());
    }

    @Test
    public void shouldFindMovieByBadTitle() throws Exception {
        Movie after = this.service.findByTitle("testing");
        assertNull(after);
    }

    @Test
    public void shouldDeleteMovieById() throws Exception {
        this.service.delete(1);
        Movie movie = this.service.findById(1);
        assertNull(movie);
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void shouldNotDeleteMovieByBadId() throws Exception {
        this.service.delete(10);
    }

    @Test
    public void shouldUpdateMovieById() throws Exception {
        Movie after = new Movie();
        after.setTitle("The Matrix");
        after.setLength(200);

        Movie updatedMovie = this.service.update(1, after);

        assertEquals(1, updatedMovie.getId());
        assertEquals(1, updatedMovie.getVersion());
        assertEquals("The Matrix", updatedMovie.getTitle());
        assertEquals(200, updatedMovie.getLength());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotUpdateMovieNoTitle() throws Exception {
        Movie movie = new Movie();
        movie.setTitle(null);
        Movie result = this.service.update(1, movie);

    }

//    @Test(expected = java.lang.AssertionError.class)
//    public void shouldNotUpdateMovieBadId() throws Exception {
//        Movie movie = new Movie();
//        movie.setTitle("testing");
//        Movie result = this.service.update(10, movie);
//
//    }
}