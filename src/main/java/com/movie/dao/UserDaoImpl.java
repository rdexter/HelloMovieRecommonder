package com.movie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.movie.entity.UserEntity;

public class UserDaoImpl implements UserDAO,UserDetailsService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Long getUserId(String userName) {
		String sql="SELECT userId from users where userName=?";
		try{
			Long userId=jdbcTemplate.queryForObject(sql, Long.class, userName);
			return userId;
		}
		catch(Exception e){
			System.out.println("Exception occured getting userId for userName"+userName+"  \n"+e);
		}
		return null;
	}
	
	public void addUser(UserEntity user) {
		System.out.println("Entering into addUse "+user);
		
		String sql="insert into users (userName, firstName, lastName, email, enable, password,mobileNo)"
				+ "value(?,?,?,?,?,?,?)";
		Integer enable=user.getEnable()==null?1:user.getEnable();
		try{
			jdbcTemplate.update(sql, user.getUsername(),user.getFirstName(),user.getLastName(),user.getEmail(),
					enable,user.getPassword(),user.getMobileNo());
		}
		catch(Exception e){
			System.out.println("Exception occured "+e);
		}
	}

	public List<UserEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String username) {
		UserDetails userDetails = null;
		UserEntity user=fetchUserDetails(username);
		
		 System.out.println("Getting access details from employee dao !!");
       if(user != null){
      	 userDetails= new User(user.getUsername(), user.getPassword(), isEnabled(user.getEnable()), true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
       }/*else{
       // Ideally it should be fetched from database and populated instance of
       // #org.springframework.security.core.userdetails.User should be returned from this method
      	 userDetails= new User(username, "password", true, true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
       }*/
       return userDetails;
	}

	private boolean isEnabled(Integer enable) {
		if(enable==null || enable==0){
			return false;
		}
		return true;
	}

	private UserEntity fetchUserDetails(String userName) {
		System.out.println("fetchUserDetailss from user dao !!");
		try{
			String sql="select * from users where username=?";
			UserEntity user=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<UserEntity>(UserEntity.class),userName);
			System.out.println("user row value: "+user);
			return user;
		}
		catch(Exception e){
			System.out.println("Exception occured: "+e);
		}
		return null;
	}

	

}
