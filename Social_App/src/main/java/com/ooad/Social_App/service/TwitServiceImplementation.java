package com.ooad.Social_App.service;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.cdi.BeanManagerQueryRewriterProvider;
import org.springframework.stereotype.Service;

import com.ooad.Social_App.Repository.TwitRepository;
import com.ooad.Social_App.exception.TwitException;
import com.ooad.Social_App.exception.UserException;
import com.ooad.Social_App.model.Twit;
import com.ooad.Social_App.model.User;
import com.ooad.Social_App.request.TwitReplyRequest;

@Service 
public class TwitServiceImplementation implements TwitService{

	
   @Autowired
   private TwitRepository twitRepository;
   
	@Override
	public Twit createTwit(Twit req,User user) throws UserException {
		
		Twit twit=new Twit();
		twit.setContent(req.getContent());
		twit.setCreatedAt(LocalDateTime.now());
		twit.setImage(req.getImage());
		twit.setUser(user);
		twit.setReply(false);
		twit.setTwit(true);
		twit.setVideo(req.getVideo());
		return twitRepository.save(twit);
	
	}

	@Override
	public List<Twit> findAllTwit() {
		// TODO Auto-generated method stub
		
			return twitRepository.findAllByIsTwitTrueOrderByCreatedAtDesc();
		
	}

	@Override
	public Twit retwit(Long twitId, User user) throws UserException, TwitException {
	  Twit twit=findById(twitId);
	  if(twit.getRetwitUser().contains(user))
	  {
		  twit.getRetwitUser().remove(user);
	  }
	  else {
		twit.getRetwitUser().add(user);	
	}
	  return twitRepository.save(twit);
	}

	@Override
	public Twit findById(Long twitId) throws TwitException {
		Twit twit=twitRepository.findById(twitId)
		.orElseThrow(()->new TwitException("Twit not Found with id"+twitId));
		return twit;
	}

	@Override
	public void deleteTwitById(Long twitId, Long userId) throws TwitException, UserException {
		Twit twit=findById(twitId);
		if(userId.equals(twit.getUser().getId())) {
			throw new UserException("You Cannot Delete Annother User Twit");
			
		}
		twitRepository.deleteById(twit.getId());
	}



	@Override
	public Twit createReply(TwitReplyRequest req, User user) throws TwitException {
		// TODO Auto-generated method stub
		Twit replyFor=findById(req.getTwitId());
		Twit twit=new Twit();
		twit.setContent(req.getContent());
		twit.setCreatedAt(LocalDateTime.now());
		twit.setImage(req.getImage());
		twit.setUser(user);
		twit.setReply(true);
		twit.setTwit(false);
	    twit.setReplyFor(replyFor);
		
		Twit savedReply=twitRepository.save(twit);
		twit.getReplyTwits().add(savedReply);
		twitRepository.save(replyFor);
		return replyFor;
		
	}

	@Override
	public List<Twit> getUserTwit(User user) {
		return twitRepository.findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(user, user.getId());
	
	}

	@Override
	public List<Twit> findByLikesContainsUser(User user) {
		return twitRepository.findByLikesUser_id(user.getId());
		
	}

	@Override
	public TwitException removeFromRetwit(Long twitId, User user) throws TwitException, UserException {
		// TODO Auto-generated method stub
		return null;
	}
	



}
