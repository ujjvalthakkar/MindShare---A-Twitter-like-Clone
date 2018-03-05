package com.neu.ujjval.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.ujjval.pojo.Tweet;
import com.neu.ujjval.pojo.User;

public class TweetDAO extends DAO{

	public TweetDAO(){	
	}
	
	public Tweet tweet(Tweet t){
		try {
			begin();
			t.getUser().setTweetCount(t.getUser().getTweetCount()+1);
			getSession().save(t);
			commit();
			close();
			return t;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public Tweet like(long tweetId,User user){
		try {
			begin();
			Tweet t=(Tweet) getSession().createQuery("from Tweet where tweetId=:tweet_Id").setParameter("tweet_Id", tweetId).uniqueResult();
			t.setLikeCount(t.getLikeCount()+1);
			t.getUserLikeList().add(user);
			getSession().save(t);	
			commit(); 
			close();
			return t;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public Tweet unlike(long tweetId,User user){
		try {
			begin();
			System.out.println("inside TweetDAO>>>>"+user+"||||>>>"+tweetId);
			Tweet t=(Tweet) getSession().createQuery("from Tweet where tweetId=:tweet_Id").setParameter("tweet_Id", tweetId).uniqueResult();
			System.out.println(t.getTweetMsg()+">>>>"+t.getLikeCount()+">>>>"+t.getUserLikeList());
			t.setLikeCount(t.getLikeCount()-1);
			List<User> u=t.getUserLikeList();
			Iterator it=u.iterator();
			while(it.hasNext()){
				User u1=(User)it.next();
				System.out.println(u1);
				if(u1.getUserId()== user.getUserId()){
					System.out.println("1234");
					it.remove();
				}
			}
			getSession().save(t);
			commit();
			close();
			return t;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public List<Tweet> getUserTweets(User u){
		try {
			begin();
			Query hqlquery = getSession().createQuery("from Tweet where user_id = :userId order by tweetDate desc");
			hqlquery.setParameter("userId", u.getUserId());
			List<Tweet> tweetList=hqlquery.list();
			for(Tweet t:tweetList){
				System.out.println("Date:"+t.getTweetDate());
			}
			commit();
			close();
			return tweetList;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public List<Tweet> getUserTweets(ArrayList<User> userList){
		System.out.println("Entered getUserTweets!!!"+userList);
		try {
			begin();
			Query hqlquery = getSession().createQuery("from Tweet where user_id IN (:userId) order by tweetDate desc");
			hqlquery.setParameterList("userId", userList.toArray());
			List<Tweet> tweetList=hqlquery.list();
			System.out.println("Leaving getUserTweets!!!>>>>"+tweetList);
			commit();
			close();
			return tweetList;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
	
	public Map<Tweet, User> getUserTweetss(ArrayList<User> userList){
		try {
			begin();
			Query hqlquery = getSession().createQuery("from Tweet where user_id IN (:userId) order by tweetDate desc");
			hqlquery.setParameterList("userId", userList.toArray());
			List<Tweet> tweetList=hqlquery.list();
			Map<Tweet, User> userTweetList=new LinkedHashMap<Tweet, User>();
			UserDAO userDao=new UserDAO();
			for(Tweet t1:tweetList){
				userTweetList.put(t1, t1.getUser());
			}
			commit();
			close();
			return userTweetList;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
}