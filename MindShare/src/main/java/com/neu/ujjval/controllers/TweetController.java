package com.neu.ujjval.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ujjval.dao.TweetDAO;
import com.neu.ujjval.dao.UserDAO;
import com.neu.ujjval.pojo.Tweet;
import com.neu.ujjval.pojo.User;

@Controller
public class TweetController {

	@Autowired
	TweetDAO tweetDao;

	@Autowired
	UserDAO userDao;

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/tweet.htm", method = RequestMethod.POST)
	public String tweet(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			User userr = new User();
			model.addAttribute("user", userr);
			return "home";
		} else {
			Tweet tweet = new Tweet();
			tweet.setLikeCount(0);
			tweet.setTweetDate(new Date());
			tweet.setTweetMsg(request.getParameter("tweetMsg"));
			tweet.setUser(user);
			tweet.setUserLikeList(new ArrayList<User>());
			tweetDao.tweet(tweet);
			//ArrayList<Tweet> followingTweetList = userDao.getFollowingTweets(user);
			Map<Tweet,User> followingTweetList=userDao.getFollowingTweetss(user);
			request.getSession().setAttribute("tweetList", followingTweetList);
			request.getSession().setAttribute("user", user);
			return "homepage";
		}
	}

	@RequestMapping(value = "/like.htm", method = RequestMethod.GET)
	public String like(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			User userr = new User();
			model.addAttribute("user", userr);
			return "home";
		} else {
			System.out.println("Entered LIKE!!!");
			long tweetId = Long.parseLong(request.getParameter("tweetId"));
			tweetDao.like(tweetId,user);
			//ArrayList<Tweet> followingTweetList = userDao.getFollowingTweets(user);
			Map<Tweet,User> followingTweetList=userDao.getFollowingTweetss(user);
			request.getSession().setAttribute("tweetList", followingTweetList);
			request.getSession().setAttribute("user", user);
			return "homepage";
		}
	}
	
	@RequestMapping(value = "/unlike.htm", method = RequestMethod.GET)
	public String unlike(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			User userr = new User();
			model.addAttribute("user", userr);
			return "home";
		} else {
			long tweetId = Long.parseLong(request.getParameter("tweetId"));
			tweetDao.unlike(tweetId,user);
			//ArrayList<Tweet> followingTweetList = userDao.getFollowingTweets(user);
			Map<Tweet,User> followingTweetList=userDao.getFollowingTweetss(user);
			request.getSession().setAttribute("tweetList", followingTweetList);
			request.getSession().setAttribute("user", user);
			return "homepage";
		}
	}

}
