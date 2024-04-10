package com.ooad.Social_App.service;

import java.util.List;

import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Like;
import com.ooad.Social_App.model.User;

public interface LikeService {
   public Like likeTwit(Long twitId,User user)throws UserException,TwitException;
   public List<Like>getAllLikes(Long twitId) throws TwitException;
}
