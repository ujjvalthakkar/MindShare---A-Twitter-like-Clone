package com.neu.ujjval.dao;

import org.hibernate.HibernateException;

import com.neu.ujjval.pojo.Follow;


public class FollowDAO extends DAO{

	public FollowDAO(){
	}

	public Follow follow(Follow f) {
		try {
			begin();
			System.out.println("Follower:"+f.getFollower().getName()+"   "+" is  Following>>>"+f.getFollowee().getName());
			f.getFollower().setFollowingCount(f.getFollower().getFollowingCount()+1);
			f.getFollowee().setFollowerCount(f.getFollowee().getFollowerCount()+1);
			getSession().save(f);
			commit();
			close();
			return f;

		} catch (HibernateException e) {
			rollback();
		}
		return null;
	}
}
