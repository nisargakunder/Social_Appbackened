package com.ooad.Social_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooad.Social_App.dto.TwitDto;
import com.ooad.Social_App.dto.mapper.TwitDtoMapper;
import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Twit;
import com.ooad.Social_App.model.User;
import com.ooad.Social_App.request.TwitReplyRequest;
import com.ooad.Social_App.response.ApiResponse;
import com.ooad.Social_App.service.TwitService;
import com.ooad.Social_App.service.UserService;

@RestController
@RequestMapping("/api/twits")
public class TwitController {
	
	@Autowired
	private TwitService twitService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<TwitDto>createTwit(@RequestBody Twit req,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 Twit twit=twitService.createTwit(req, user);
		 
		 TwitDto twitDto=TwitDtoMapper.toTwitDto(twit, user);
		 
				return new ResponseEntity<>(twitDto,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/reply")
	public ResponseEntity<TwitDto>ReplyTwit(@RequestBody TwitReplyRequest req,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 Twit twit=twitService.createReply(req, user);
		 
		 TwitDto twitDto=TwitDtoMapper.toTwitDto(twit, user);
		 
				return new ResponseEntity<>(twitDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{twitId}/retwit")
	public ResponseEntity<TwitDto>retwit(@PathVariable Long twitId,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 Twit twit=twitService.retwit(twitId, user);
		 
		 TwitDto twitDto=TwitDtoMapper.toTwitDto(twit, user);
		 
				return new ResponseEntity<>(twitDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/{twitId}")
	public ResponseEntity<TwitDto>findTwitById(@PathVariable Long twitId,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 Twit twit=twitService.findById(twitId);
		 
		 TwitDto twitDto=TwitDtoMapper.toTwitDto(twit, user);
		 
				return new ResponseEntity<>(twitDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{twitId}")
	public ResponseEntity<ApiResponse>deletetwit(@PathVariable Long twitId,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 twitService.deleteTwitById(twitId,user.getId());
		 
		 ApiResponse res=new ApiResponse();
		 res.setMessage("Twit Deleted Succesfully");
		 res.setStatus(true);
		 
				return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TwitDto>>getAllTwits(
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 List<Twit> twits=twitService.findAllTwit();
		 
		 List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits, user);
		 
				return new ResponseEntity<>(twitDtos,HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TwitDto>>getUserAllTwits(@PathVariable Long userId,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 List<Twit> twits=twitService.getUserTwit(user);
		
		 List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits, user);
		 
				return new ResponseEntity<>(twitDtos,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/likes")
	public ResponseEntity<List<TwitDto>>findTwitByLikesContainsUser(@PathVariable Long userId,
			@RequestHeader("Authorization")String jwt) throws UserException,TwitException{
		
		 User user=userService.findUserProfileByJwt(jwt);
		 
		 List<Twit> twits=twitService.findByLikesContainsUser(user);
		
		 List<TwitDto> twitDtos=TwitDtoMapper.toTwitDtos(twits, user);
		 
				return new ResponseEntity<>(twitDtos,HttpStatus.OK);
	}
	
	

}
