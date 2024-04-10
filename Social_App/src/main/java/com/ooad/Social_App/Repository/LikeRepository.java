package com.ooad.Social_App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.ooad.Social_App.model.Like;


public interface LikeRepository extends JpaRepository<Like,Long>{
	@Query("Select l from Like l where l.user.id=:userId And l.twit.id=:twitId")
	public Like isLikeExist(@Param("userId")Long userId,@Param("twitId")Long twitId);
	@Query("Select l from Like l where l.twit.id=:twitId")
	public List<Like> findByTwitId(@Param("twitId")Long twitId);
	

}
