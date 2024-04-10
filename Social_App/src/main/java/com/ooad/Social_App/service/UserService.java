package com.ooad.Social_App.service;

import java.util.List;

import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.User;

public interface UserService {
	
public User findUserById(Long userId)  throws UserException; 
public User findUserProfileByJwt(String jwt) throws UserException;
public User updateUser(Long userId,User req)throws UserException;
public User followUser(Long userId,User user)throws UserException;
public List<User> searchUser(String query);
}
