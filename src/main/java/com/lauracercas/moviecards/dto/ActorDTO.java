package com.lauracercas.moviecards.dto;

import java.util.Date;
import java.util.List;

import com.lauracercas.moviecards.model.Movie;

public class ActorDTO {

      private Integer id;

    private String name;

    private Date birthDate;
   
    private Date deadDate;
   
    private String country;
    private List<Movie> movies;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getDeadDate() {
        return deadDate;
    }
    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    
}