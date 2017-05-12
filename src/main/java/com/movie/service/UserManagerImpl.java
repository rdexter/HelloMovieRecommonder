package com.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.dao.UserDAO;
import com.movie.entity.UserEntity;
@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void addUser(UserEntity user) {
		userDao.addUser(user);
	}

	public List<UserEntity> getAllUsers() {
		return userDao.getAllUsers();
	}
	@Transactional
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
	}

	public Long getUserId(String userName) {
		return userDao.getUserId(userName);
	}
	
	
}
