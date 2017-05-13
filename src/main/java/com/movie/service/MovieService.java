package com.movie.service;

import java.util.List;

import com.movie.entity.Movie;
import com.movie.entity.MovieUserMatrix;


public interface MovieService {
	
	List<Movie> getRecommondation(long userId);
	
	int addMovieRating(MovieUserMatrix matrix);
	
	List<Movie> getMovies(int pagenumber);

	int updateMovieRating(MovieUserMatrix matrix);

	List<Movie> getMovies(Long userId, int i);

}
