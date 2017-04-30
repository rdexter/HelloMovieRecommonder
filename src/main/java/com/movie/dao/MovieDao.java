package com.movie.dao;

import java.util.List;

import com.movie.entity.Movie;
import com.movie.entity.MovieUserMatrix;


public interface MovieDao {
	
	
	List<Long> getUsersWhoLikeTheMovie(long movieId);
	
	List<Long> getUsersWhoDisLikeTheMovie(long movieId);
	
	List<Long> getMoviesLikeByTheUser(long userId);
	
	List<Long> getMoviesDisLikeByTheUser(long userId);

	List<Long> getMoviesNotRatedByTheUsers(long userId);

	List<Movie> getMovies(long userId, int pageNumber);

	int updateMovieRating(MovieUserMatrix matrix);
	
	int addMovieRating(MovieUserMatrix matrix);

	List<Movie> getMoviesByUserIds(List<Long> movieIdArray);


}
