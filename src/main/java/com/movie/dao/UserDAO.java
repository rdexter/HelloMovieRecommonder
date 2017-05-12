package com.movie.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.movie.entity.UserEntity;


public interface UserDAO 
{
    public void addUser(UserEntity user);
    public List<UserEntity> getAllUsers();
    public void deleteUser(Integer userId);
    public UserDetails loadUserByUsername(String username);
	public Long getUserId(String userName);
}