package com.movie.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.movie.entity.Movie;
import com.movie.entity.MovieUserMatrix;
import com.movie.service.MovieService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
//@RequestMapping("/api")
public class MovieController {
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(MovieController.class);

	@Autowired
	MovieService service;

	@RequestMapping(value = "/updateMovieRating", method = RequestMethod.POST)
	@ResponseBody
	public int updateMovieRating(@RequestBody String jsonString){
		long start = System.currentTimeMillis();
		String apiName="updateMovieRating";
		LOGGER.info("Entering into updateMovieRating : " + jsonString);
		JSONObject reqJson=(JSONObject) JSONSerializer.toJSON(jsonString);
		MovieUserMatrix matrix= new MovieUserMatrix();
		boolean isValidReqJson=validateJson(reqJson,matrix);

		if(isValidReqJson){
			int isUpdatedSuccess=service.updateMovieRating(matrix);
			return isUpdatedSuccess;
		}

		logProcessingTime(apiName, start);
		return 0;
	}

	private boolean validateJson(JSONObject reqJson,MovieUserMatrix matrix) {
		if(reqJson.has("userId")){
			matrix.setUserId(reqJson.getLong("userId"));
		}
		else{
			return false;
		}
		if(reqJson.has("movieId")){
			matrix.setMovieId(reqJson.getLong("movieId"));
		}
		else{
			return false;
		}
		if(reqJson.has("rating")){
			matrix.setRating(reqJson.getInt("rating"));
		}
		else{
			return false;
		}
		if(reqJson.has("like")){
			matrix.setUserId(reqJson.getLong("like"));
		}
		return true;
	}

	@RequestMapping(value = "/getUserRecommondation", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getUserRecommondation(@RequestParam("userId") long userId){
		long start = System.currentTimeMillis();
		LOGGER.info("Entering into getUserRecommondation : " + userId);
		JSONObject respJson=new JSONObject();
		String apiName="getUserRecommondation";
		List<Long> movieIds=service.getRecommondation(userId);
		respJson.put("Recommonded MovieIds", movieIds);
		logProcessingTime(apiName, start);
		return respJson;
	}
	
	
	/*@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray home(@RequestParam("userId") long userId){
		long start = System.currentTimeMillis();
		String apiName="home";
		LOGGER.info("REQUEST home : ");
		List<Movie> movieList=service.getMovies(1);
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(movieList);
		logProcessingTime(apiName, start);
		return jsonArray;
	}*/
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	//public ModelAndView home(@RequestParam("userId") long userId,@RequestParam("offset") int offset) {
	public ModelAndView home() {
		long start = System.currentTimeMillis();
		String apiName="home";
		LOGGER.info("REQUEST home : ");
		long userId=196;
		int offset=1;
		List<Movie> movieList=service.getMovies(userId,offset);
		ModelAndView model = new ModelAndView();
		model.addObject("movieList", movieList);
		model.addObject("msg", "Hello moto");
		LOGGER.info("movieList home : "+movieList.toString());
		logProcessingTime(apiName, start);
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray home1(@RequestParam("userId") long userId,@RequestParam("offset") int offset) {
		long start = System.currentTimeMillis();
		String apiName="home";
		LOGGER.info("REQUEST home : ");
		List<Movie> movieList=service.getMovies(userId,offset);
		ModelAndView model = new ModelAndView();
		model.addObject("movieList", movieList);
		model.addObject("msg", "Hello moto");
		LOGGER.info("movieList home : "+movieList.toString());
		JSONArray jsonArray=new JSONArray();
		jsonArray.addAll(movieList);
		logProcessingTime(apiName, start);
		model.setViewName("home");
		return jsonArray;
	}
	
	 @RequestMapping(value = "/home2", method = RequestMethod.GET)
	   public String home2(ModelMap model) {
		 long start = System.currentTimeMillis();
			String apiName="home";
			LOGGER.info("REQUEST home : ");
			long userId=196;
			int offset=1;
			List<Movie> movieList=service.getMovies(userId,offset);
			model.addObject("movieList", movieList);
			model.addObject("msg", "Hello moto");
			LOGGER.info("movieList home : "+movieList.toString());
			logProcessingTime(apiName, start);
			return "home";
	   }

	
	
	@RequestMapping(value = "/addMovieLikeByTheUser", method = RequestMethod.GET)
	@ResponseBody
	public int addMovieLikeByTheUser(@RequestBody String jsonString){
		long start = System.currentTimeMillis();
		String apiName="addMovieLikeByTheUser";

		JSONObject reqJson=(JSONObject) JSONSerializer.toJSON(jsonString);
		MovieUserMatrix matrix= new MovieUserMatrix();
		boolean isValidReqJson=validateJson(reqJson,matrix);
		if(isValidReqJson){
			int response=service.addMovieRating(matrix);
			LOGGER.info("response: "+response);
			return response;
		}
		logProcessingTime(apiName, start);
		return 0;
	}
	private void logProcessingTime(String api, long start) {
		System.out.println("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
		LOGGER.info("Processing time : " + api + " : " + (System.currentTimeMillis() - start) + " ms");
	}
}
