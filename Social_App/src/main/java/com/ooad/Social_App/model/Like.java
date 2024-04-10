// Source code is decompiled from a .class file using FernFlower decompiler.
package com.ooad.Social_App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="likes")
public class Like {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   
   @ManyToOne
   private User user;
   
   @ManyToOne
   private Twit twit;

   
}
