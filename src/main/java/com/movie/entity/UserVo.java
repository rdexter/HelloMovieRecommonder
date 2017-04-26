package com.movie.entity;

import java.util.List;

public class UserVo {
	private long userId;
	private String userName;
	List<Long> listOfMoviesLikedByTheUser;
	List<Long> listOfMoviesDisLikedByTheUser;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setListOfMoviesDisLikedByTheUser(List<Long> listOfMoviesDisLikedByTheUser) {
		this.listOfMoviesDisLikedByTheUser = listOfMoviesDisLikedByTheUser;
	}
	public List<Long> getListOfMoviesLikedByTheUser() {
		return listOfMoviesLikedByTheUser;
	}
	public void setListOfMoviesLikedByTheUser(List<Long> listOfMoviesLikedByTheUser) {
		this.listOfMoviesLikedByTheUser = listOfMoviesLikedByTheUser;
	}
	public List<Long> getListOfMoviesDisLikedByTheUser() {
		return listOfMoviesDisLikedByTheUser;
	}
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userName=" + userName + ", listOfMoviesLikedByTheUser=" + listOfMoviesLikedByTheUser
			+ ", listOfMoviesDisLikedByTheUser=" + listOfMoviesDisLikedByTheUser + "]";
	}
	
}
