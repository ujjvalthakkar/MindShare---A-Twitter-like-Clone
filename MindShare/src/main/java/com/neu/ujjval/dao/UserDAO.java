package com.neu.ujjval.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.neu.ujjval.pojo.Follow;
import com.neu.ujjval.pojo.Tweet;
import com.neu.ujjval.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User registerUser(User u) {
		try {
			begin();
			System.out.println("inside UserDAO");
			getSession().save(u);
			commit();
			close();
			return u;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public User updateUser(User u) {
		System.out.println("entereddddd");
		try {
			begin();
			System.out.println("inside UserDAO");
			getSession().saveOrUpdate(u);
			commit();
			close();
			return u;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public List<User> getUserList() {
		try {
			begin();
			Query hqlquery = getSession().createQuery("from User");
			commit();
			List<User> userList = hqlquery.list();
			close();
			return userList;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public List<User> getUserList(User u) {
		try {
			List<User> userList = new ArrayList<User>();
			begin();
			Session s = getSession();
			Query hqlquery = s.createQuery("from User where userid <> :userid");
			hqlquery.setParameter("userid", u.getUserId());
			List<User> userList1 = hqlquery.list();
			Query hqlquery1 = s.createQuery("from Follow where follower_id = :userid").setParameter("userid",
					u.getUserId());
			commit();
			List<Follow> followingList = hqlquery1.list();
			for (User u1 : userList1) {
				int i = 0;
				for (Follow f : followingList) {
					if (f.getFollowee().getUserId() == u1.getUserId()) {
						i++;
					}
				}
				if (i == 0)
					userList.add(u1);
			}
			close();
			return userList;
		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public User getUser(long id) {
		try {
			begin();
			Query hqlquery = getSession().createQuery("from User where userid = :userid");
			hqlquery.setParameter("userid", id);
			commit();
			User u = (User) hqlquery.uniqueResult();
			close();
			return u;
		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public User validateUser(String email, String password) {
		try {
			begin();
			Query hqlquery = getSession().createQuery("from User");
			commit();
			List<User> userList = hqlquery.list();
			for (User u : userList) {
				if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
					close();
					return u;
				}
			}
		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public ArrayList<User> getFollowing(User user) { // returns the list of
														// users the current
														// user(follower) is
														// following
		ArrayList<User> followingList = new ArrayList<User>();
		try {
			begin();
			Query hqlquery = getSession().createQuery("from Follow where follower_id = :followerId");
			hqlquery.setParameter("followerId", user.getUserId());
			commit();
			List<Follow> followList = hqlquery.list(); // List(Follow) of user's
														// followers
			List<User> userList = getUserList();

			for (Follow f : followList) {
				followingList.add(f.getFollowee());
			}
			close();
			return followingList;
		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}

	public ArrayList<Tweet> getFollowingTweets(User user) { // returns all the
															// tweets by all the
															// users the current
															// user is following
															// and the user's
															// tweet as well
		ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
		TweetDAO tweetDao = new TweetDAO();
		ArrayList<User> followingList = getFollowing(user); // retrieve who the
															// user is following
		followingList.add(user);
		tweetList = new ArrayList<Tweet>(tweetDao.getUserTweets(followingList));
		close();
		return tweetList;
	}

	public Map<Tweet, User> getFollowingTweetss(User user) { // returns all the
		// tweets by all the
		// users the current
		// user is following
		// and the user's
		// tweet as well
		Map<Tweet, User> tweetList = new LinkedHashMap<Tweet, User>();
		TweetDAO tweetDao = new TweetDAO();
		ArrayList<User> followingList = getFollowing(user); // retrieve who the
		// user is following
		followingList.add(user);
		tweetList = new LinkedHashMap<Tweet, User>(tweetDao.getUserTweetss(followingList));
		close();
		return tweetList;
	}
}
