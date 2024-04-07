// Source code is decompiled from a .class file using FernFlower decompiler.
package com.ooad.Social_App.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
   @Id
   @GeneratedValue
   private Long id;
   private String fullname;
   private String lastname;
   private String location;
   private String website;
   private String birthdate;
   private String email;
   private String password;
   private String mobile;
   private String image;
   private String backgroundimage;
   private String bio;
   private boolean reg_user;
   @JsonIgnore
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   private List<Twit> twit=new ArrayList<>();
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   private List<like> likes=new ArrayList<>();
   @ManyToMany
   private List<User> followers=new ArrayList<>();
   @JsonIgnore
   @ManyToMany
   private List<User> followings=new ArrayList<>();

   
}
