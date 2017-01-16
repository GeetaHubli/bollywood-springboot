package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {
    private IMovieRepository repository;

    @Autowired
    public MovieService(IMovieRepository repository) {
        this.repository = repository;
    }

    public Movie create(Movie m){
        return  this.repository.save(m);
    }

    public Movie findById(int id){
        return this.repository.findOne(id);
    }

    public Iterable<Movie> findAll(){
        return this.repository.findAll();
    }

    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void delete(int id){
        this.repository.delete(id);
    }

    public Movie update(int id, Movie newValueMovie){
        Movie movie = findById(id);
        if(movie != null) {
            movie.setTitle(newValueMovie.getTitle());
            movie.setWatched(newValueMovie.isWatched());
            movie.setRating(newValueMovie.getRating());
            movie.setReleased(newValueMovie.getReleased());
            movie.setLength(newValueMovie.getLength());
            Movie updateMovie = this.repository.save(movie);
            return updateMovie;
        }
        return null;
    }
}
