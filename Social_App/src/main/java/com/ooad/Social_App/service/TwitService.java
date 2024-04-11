package com.ooad.Social_App.service;

import java.util.List;

import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Twit;
import com.ooad.Social_App.model.User;
import com.ooad.Social_App.request.TwitReplyRequest;

public interface TwitService {
//create twit, find twit ,retweet ,find tweet by id,delete tweet,remove user retweet,get user tweet
	public Twit createTwit(Twit req,User user)throws UserException;
	public List<Twit>findAllTwit();
	public Twit retwit(Long twitId,User user) throws UserException,TwitException;
	
	public Twit findById(Long twitId) throws TwitException;
	public void deleteTwitById(Long twitId,Long userId) throws TwitException,UserException;
	public TwitException removeFromRetwit(Long twitId,User user)throws TwitException,UserException;
	public Twit createReply(TwitReplyRequest req,User user)throws TwitException;
	public List<Twit> getUserTwit(User user);
	public List<Twit>findByLikesContainsUser(User user);
	
}
