package com.movie.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.movie.entity.UserEntity;


public interface UserManager {
	
	public void addUser(UserEntity user);
   public List<UserEntity> getAllUsers();
   public void deleteUser(Integer userId);
   public Long getUserId(String userName);
	public UserDetails findByUsername(String userName);

}
