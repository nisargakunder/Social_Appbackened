package com.ooad.Social_App.controller;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooad.Social_App.dto.LikeDto;
import com.ooad.Social_App.dto.mapper.LikeDtoMapper;
import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Like;
import com.ooad.Social_App.model.User;
import com.ooad.Social_App.service.LikeService;
import com.ooad.Social_App.service.UserService;

@RestController
@RequestMapping("/api")
public class LikeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/{twitId}/likes")
	public ResponseEntity<LikeDto>likeTwit(@PathVariable Long twitId,
			@RequestHeader("Authorization")String jwt)throws UserException,TwitException{
		
		User user=userService.findUserProfileByJwt(jwt);
		Like like=likeService.likeTwit(twitId, user);
		
		LikeDto likeDto=LikeDtoMapper.toLikeDto(like, user);

		return new ResponseEntity<LikeDto>(likeDto,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/twit/{twitId}")
	public ResponseEntity<List<LikeDto>>getAllLikes(@PathVariable Long twitId,
			@RequestHeader("Authorization")String jwt)throws UserException,TwitException{
		
		User user=userService.findUserProfileByJwt(jwt);
		List<Like> like=likeService.getAllLikes(twitId);
		
		List<LikeDto> likeDtos=LikeDtoMapper.toLikeDtos(like, user);

		return new ResponseEntity<>(likeDtos,HttpStatus.CREATED);
		
	}
}







































