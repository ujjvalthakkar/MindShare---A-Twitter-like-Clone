package com.neu.ujjval.controllers;

import java.io.File;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.neu.ujjval.validator.UserValidator;
import com.neu.ujjval.dao.UserDAO;
import com.neu.ujjval.pojo.Tweet;
import com.neu.ujjval.pojo.User;

@Controller
public class UserController {

	@Autowired
	UserDAO userDao;

	@Autowired
	ServletContext servletContext;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	public String logoutUser(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		User user = new User();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logoutUserr(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		User user = new User();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.GET)
	public String registerNewUser(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/userprofile.htm", method = RequestMethod.POST)
	public String showUserProfile(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		model.addAttribute("user", user);
		return "userprofile";
	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, HttpServletRequest request) {

		user.setFollowerCount(0);
		user.setFollowingCount(0);
		user.setTweetCount(0);
		CommonsMultipartFile photoInMemory = user.getPhoto();
		String fileName = photoInMemory.getOriginalFilename();
		File localFile = new File(
				"C:\\Users\\ujjva\\OneDrive\\Documents\\SpringProjects\\MindShare\\src\\main\\webapp\\resources\\images",
				fileName);
		try {
			photoInMemory.transferTo(localFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File is stored at" + localFile.getPath());
		user.setFilename(fileName);
		user = userDao.registerUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("usertoFollowList", userDao.getUserList(user));
		// ArrayList<Tweet> followingTweetList =
		// userDao.getFollowingTweets(user);
		Map<Tweet, User> followingTweetList = userDao.getFollowingTweetss(user);
		session.setAttribute("tweetList", followingTweetList);
		return "homepage";
	}

	@RequestMapping(value = "/update.htm", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, HttpServletRequest request) {
		System.out.println("Entered!!");
		User orgUser = userDao.getUser(Long.parseLong(request.getParameter("userid")));
		System.out.println("Entered!!"+orgUser+">>>>>>>>>>>>"+user);
		String email = user.getEmail();
		String name = user.getName();
		CommonsMultipartFile photoInMemory = user.getPhoto();
		String fileName = photoInMemory.getOriginalFilename();
		if (email.equals("") && name.equals("") && fileName.equals("")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("usertoFollowList", userDao.getUserList(user));
			Map<Tweet, User> followingTweetList = userDao.getFollowingTweetss(user);
			session.setAttribute("tweetList", followingTweetList);
			return "homepage";
		}

		else {
			if (!email.equals("")) {
				if (!email.equals(orgUser.getEmail())) {
					orgUser.setEmail(email);
				}
			}
			if (!name.equals("")) {
				if (!name.equals(orgUser.getName())) {
					orgUser.setName(name);
				}
			}

			if (!fileName.equals("")) {
				File localFile = new File(
						"C:\\Users\\ujjva\\OneDrive\\Documents\\SpringProjects\\MindShare\\src\\main\\webapp\\resources\\images",
						fileName);
				try {
					photoInMemory.transferTo(localFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("File is stored at" + localFile.getPath());
				orgUser.setFilename(fileName);
			}

			user = userDao.updateUser(orgUser);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("usertoFollowList", userDao.getUserList(user));
			Map<Tweet, User> followingTweetList = userDao.getFollowingTweetss(user);
			session.setAttribute("tweetList", followingTweetList);
			return "homepage";
		}
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String loginUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String email = request.getParameter("useremail");
		String password = request.getParameter("userpwd");
		User u = userDao.validateUser(email, password);
		if (u != null) {
			request.getSession().setAttribute("user", u);
			Map<Tweet, User> followingTweetList = userDao.getFollowingTweetss(u);
			request.getSession().setAttribute("tweetList", followingTweetList);
			request.getSession().setAttribute("usertoFollowList", userDao.getUserList(u));
			return "homepage";
		} else {
			User user = new User();
			model.addAttribute("user", user);
			return "home";
		}
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String showloginUser(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
