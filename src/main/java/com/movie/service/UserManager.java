package com.movie.service;

import java.util.List;

import com.movie.entity.UserEntity;


public interface UserManager {
	
	public void addUser(UserEntity user);
   public List<UserEntity> getAllUsers();
   public void deleteUser(Integer userId);

}
