package com.ooad.Social_App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ooad.Social_App.model.User;

public interface UserRepository  extends JpaRepository<User,Long>{
   public User findByEmail(String email);
 @Query("select distinct u from User u where u.fullname like %:query% or u.email like %:query%")
   public List<User> searchUser(@Param("query")String query);
}
