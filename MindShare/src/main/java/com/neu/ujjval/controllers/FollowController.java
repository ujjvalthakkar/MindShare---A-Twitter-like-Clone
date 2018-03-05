package com.neu.ujjval.controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.ujjval.dao.FollowDAO;
import com.neu.ujjval.dao.UserDAO;
import com.neu.ujjval.pojo.Follow;
import com.neu.ujjval.pojo.Tweet;
import com.neu.ujjval.pojo.User;

@Controller
public class FollowController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	FollowDAO followDao;

	@Autowired
	UserDAO userDao;

	@RequestMapping(value = "/follow.htm", method = RequestMethod.POST)
	public String followUser(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			User userr = new User();
			model.addAttribute("user", userr);
			return "home";
		} else {
			Follow f = new Follow();
			User follower = userDao.getUser(Long.parseLong(request.getParameter("follower_userid")));
			User followee = userDao.getUser(Long.parseLong(request.getParameter("followee_userid")));
			System.out.println("follower>>"+follower+"|||||"+"followee"+followee);
			f.setFollowee(followee);
			f.setFollower(follower);
			followDao.follow(f);
			userDao.registerUser(follower);
			userDao.registerUser(followee);
			request.getSession().setAttribute("usertoFollowList", userDao.getUserList(user));
			//ArrayList<Tweet> followingTweetList = userDao.getFollowingTweets(user);
			Map<Tweet,User> followingTweetList=userDao.getFollowingTweetss(user);
			request.getSession().setAttribute("tweetList", followingTweetList);
			request.getSession().setAttribute("user", user);
			return "homepage";
		}
	}

	@RequestMapping(value = "/unfollow.htm", method = RequestMethod.POST)
	public String unfollowUser(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			User userr = new User();
			model.addAttribute("user", userr);
			return "home";
		} else {
			Follow f = new Follow();
			User follower = userDao.getUser(Long.parseLong(request.getParameter("follower_userid")));
			User followee = userDao.getUser(Long.parseLong(request.getParameter("followee_userid")));
			f.setFollowee(followee);
			f.setFollower(follower);
			// followDao.unfollow(f);
			//ArrayList<Tweet> followingTweetList = userDao.getFollowingTweets(user);
			Map<Tweet,User> followingTweetList=userDao.getFollowingTweetss(user);
			request.getSession().setAttribute("tweetList", followingTweetList);
			return "homepage";
		}
	}
}
