package com.movie.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.entity.Movie;
import com.movie.entity.MovieUserMatrix;
import com.movie.service.MovieService;
import com.movie.service.SecurityService;
import com.movie.service.UserManager;

@Controller
//@RequestMapping("/api")
public class MovieController {
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieController.class);

	@Autowired
	MovieService movieService;

	@Autowired
	UserManager userService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/updateMovieRating", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateMovieRating(ModelMap model,@RequestParam("user") String userName, @RequestParam("movieId") long movieId,
			@RequestParam("like") int like){
		long start = System.currentTimeMillis();
		String apiName="updateMovieRating";
		LOGGER.info("Entering into updateMovieRating user: " + userName+"  movieId: "+movieId+"  like: "+like);
		MovieUserMatrix matrix= new MovieUserMatrix();
		Long userId=userService.getUserId(userName);
		if(userId==null){
			userId=196l;
		}else{
			LOGGER.info("userId recerived: "+userId);
		}
		matrix.setUserId(userId);
		matrix.setMovieId(movieId);
		
		if(like==1){
			int rati= (int) ((userId%6)>=3?(userId%6):4);
			matrix.setRating(rati);
		}
		else{
			int rati=(int) ((userId%6)<3?(userId%6):1);
			matrix.setRating(rati);
		}
		int isUpdatedSuccess=movieService.updateMovieRating(matrix);
		LOGGER.info("isUpdatedSuccess: "+isUpdatedSuccess);
		
		List<Movie> recommondedMovieList=movieService.getRecommondation(userId);
		int pageNumber=1;
		List<Movie> movieList=null;
		if(userId!= null){
			movieList=movieService.getMovies(userId,pageNumber+1);
		}else{
			movieList=movieService.getMovies(pageNumber+1);
		}
		LOGGER.info("recommondedMovieList home : "+recommondedMovieList);
		LOGGER.info("movieList home : "+movieList);
		model.addObject("recommondedMovieList", recommondedMovieList);
		model.addObject("movieList", movieList);
		model.addObject("pageNumber", pageNumber);
		
		logProcessingTime(apiName, start);
		return "home";
	}


	@RequestMapping(value = "/getUserRecommondation", method = {RequestMethod.GET,RequestMethod.POST})
	public String getUserRecommondation(ModelMap model,@RequestParam("user") String userName, @RequestParam("pageNumber") int pageNumber){
		String apiName="getUserRecommondation";
		long start = System.currentTimeMillis();
		LOGGER.info("Entering into nextMoviesList : " + userName+"  pageNumber: "+pageNumber);
		Long userId=userService.getUserId(userName);
		if(userId==null){
			userId=196l;
		}else{
			LOGGER.info("null userId recerived");
		}
		List<Movie> recommondedMovieList=movieService.getRecommondation(userId);
		List<Movie> movieList=null;
		if(userId!= null){
			movieList=movieService.getMovies(userId,pageNumber+1);
		}else{
			movieList=movieService.getMovies(pageNumber+1);
		}
		LOGGER.info("recommondedMovieList home : "+recommondedMovieList);
		LOGGER.info("movieList home : "+movieList);
		model.addObject("recommondedMovieList", recommondedMovieList);
		model.addObject("movieList", movieList);
		model.addObject("pageNumber", pageNumber);

		logProcessingTime(apiName, start);
		return "home";
	}

	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2(ModelMap model) {
		long start = System.currentTimeMillis();
		String apiName="home2";
		int pageNumber=1;
		LOGGER.info("REQUEST home2 : ");
		List<Movie> movieList=movieService.getMovies(pageNumber);
		model.addObject("movieList", movieList);
		model.addObject("pageNumber", pageNumber);
		LOGGER.info("movieList home : "+movieList);
		logProcessingTime(apiName, start);
		return "home";
	}


	@RequestMapping(value = "/next", method = {RequestMethod.GET,RequestMethod.POST})
	public String nextMoviesList(ModelMap model,@RequestParam("user") String userName, @RequestParam("pageNumber") int pageNumber) {
		String apiName="nextMovies";
		long start = System.currentTimeMillis();
		LOGGER.info("Entering into nextMoviesList : " + userName+"  pageNumber: "+pageNumber);
		Long userId=userService.getUserId(userName);
		if(userId==null){
			userId=196l;
		}else{
			LOGGER.info("userId recerived "+userId);
		}
		pageNumber=pageNumber+1;
		List<Movie> recommondedMovieList=movieService.getRecommondation(userId);
		List<Movie> movieList=null;
		if(userId!= null){
			movieList=movieService.getMovies(userId,pageNumber+1);
		}else{
			movieList=movieService.getMovies(pageNumber+1);
		}
		LOGGER.info("recommondedMovieList home : "+recommondedMovieList);
		LOGGER.info("movieList home : "+movieList);
		model.addObject("recommondedMovieList", recommondedMovieList);
		model.addObject("movieList", movieList);
		model.addObject("pageNumber", pageNumber);

		logProcessingTime(apiName, start);
		return "home";
	}

	@RequestMapping(value = "/back", method = {RequestMethod.GET,RequestMethod.POST})
	public String previousMoviesList(ModelMap model,@RequestParam("user") String userName, @RequestParam("pageNumber") int pageNumber) {
		String apiName="back";
		long start = System.currentTimeMillis();
		LOGGER.info("Entering into previousMoviesList : " + userName+"  pageNumber: "+pageNumber);
		Long userId=userService.getUserId(userName);
		if(userId==null){
			userId=196l;
		}else{
			LOGGER.info("null userId recerived");
		}
		if(pageNumber<=1){
			pageNumber=2;
		}else{
			pageNumber=pageNumber-1;
		}
		List<Movie> recommondedMovieList=movieService.getRecommondation(userId);
		List<Movie> movieList=null;
		if(userId!= null){
			movieList=movieService.getMovies(userId,pageNumber+1);
		}else{
			movieList=movieService.getMovies(pageNumber+1);
		}
		LOGGER.info("recommondedMovieList home : "+recommondedMovieList);
		LOGGER.info("movieList home : "+movieList);
		model.addObject("recommondedMovieList", recommondedMovieList);
		model.addObject("movieList", movieList);
		model.addObject("pageNumber", pageNumber);

		logProcessingTime(apiName, start);
		return "home";
	}


	private void logProcessingTime(String api, long start) {
		System.out.println("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
		LOGGER.info("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
	}
}
