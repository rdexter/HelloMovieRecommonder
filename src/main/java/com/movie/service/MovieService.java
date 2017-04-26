package com.movie.service;

import java.util.List;

import com.movie.entity.Movie;
import com.movie.entity.MovieUserMatrix;


public interface MovieService {
	
	List<Long> getRecommondation(long userId);
	
	int addMovieRating(MovieUserMatrix matrix);
	
	List<Movie> getMovies(long userId, int pageNumber);

	int updateMovieRating(MovieUserMatrix matrix);

}
