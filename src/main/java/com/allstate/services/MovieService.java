package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public Movie search(Movie m){ return  this.repository.findOne(m.getId()); }
}
