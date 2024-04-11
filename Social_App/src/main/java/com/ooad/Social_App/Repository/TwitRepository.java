package com.ooad.Social_App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ooad.Social_App.model.Twit;
import com.ooad.Social_App.model.User;

import java.util.List;;

public interface TwitRepository extends JpaRepository<Twit,Long> {
	
	List<Twit>findAllByIsTwitTrueOrderByCreatedAtDesc();
	List<Twit>findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(User user,Long userId);
	List<Twit>findByLikesContainingOrderByCreatedAtDesc(User user);
	
	@Query("Select t from Twit t Join t.likes l where l.user.id=:userId")
	List<Twit>findByLikesUser_id(Long userId);
	
	
	
}


