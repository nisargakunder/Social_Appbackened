package com.ooad.Social_App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooad.Social_App.Repository.LikeRepository;
import com.ooad.Social_App.Repository.TwitRepository;
import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Like;
import com.ooad.Social_App.model.Twit;
import com.ooad.Social_App.model.User;
@Service
public class LikeServiceImplementation implements LikeService{
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    TwitService twitService;
    @Autowired
    TwitRepository twitRepository;
	@Override
	public Like likeTwit(Long twitId, User user) throws UserException, TwitException {
		Like isLikeExist=likeRepository.isLikeExist(user.getId(),twitId);
		if(isLikeExist!=null) {
			likeRepository.deleteById(isLikeExist.getId());
			return isLikeExist;
		}
		Twit twit=twitService.findById(twitId);
		Like like=new Like();
		like.setTwit(twit);
		like.setUser(user);
		Like savedLike=likeRepository.save(like);
		twit.getLikes().add(savedLike);
		twitRepository.save(twit);
		return savedLike;
		
	}

	@Override
	public List<Like> getAllLikes(Long twitId) throws TwitException {
		Twit twit=twitService.findById(twitId);
		List<Like>likes=likeRepository.findByTwitId(twitId);
		return likes;
	}
	

}
