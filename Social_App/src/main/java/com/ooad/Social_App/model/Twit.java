// Source code is decompiled from a .class file using FernFlower decompiler.
package com.ooad.Social_App.model;

import java.util.ArrayList;
import jakarta.persistence.OneToMany;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Twit {
   @Id
   @GeneratedValue
   private Long id;
   @ManyToOne
   private User user;
   private String content;
   private String image;
   private String video;
   @OneToMany(mappedBy = "twit",cascade = CascadeType.ALL)
   private List<like> likes=new ArrayList<>();
   @OneToMany
   private List<Twit> replyTwits=new ArrayList<>();
   private List<User> retwitUser=new ArrayList<>();
   @ManyToOne
   private Twit replyFor;
   private boolean isReply;
   private boolean isTwit;

}
