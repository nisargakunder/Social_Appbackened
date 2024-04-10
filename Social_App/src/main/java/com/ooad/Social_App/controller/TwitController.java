package com.ooad.Social_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooad.Social_App.service.TwitService;
import com.ooad.Social_App.service.UserService;

@RestController
@RequestMapping("/api/twits")
public class TwitController {
@Autowired
private TwitService twitService;
private UserService userService;

}
