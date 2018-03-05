package com.neu.ujjval.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tweet")
public class Tweet {

	public Tweet() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tweetId", unique = true, nullable = false)
	private long tweetId;

	@Column(name = "tweetMsg", nullable = false, length = 140)
	private String tweetMsg;

	@Column(name = "tweetDate")
	private Date tweetDate;

	@Column(name = "likeCount")
	private long likeCount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity=User.class)
	@JoinColumn(name = "like_users", nullable = false)
	private List<User> userLikeList;

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweetMsg() {
		return tweetMsg;
	}

	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}

	public Date getTweetDate() {
		return tweetDate;
	}

	public void setTweetDate(Date tweetDate) {
		this.tweetDate = tweetDate;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserLikeList() {
		return userLikeList;
	}

	public void setUserLikeList(ArrayList<User> userLikeList) {
		this.userLikeList = userLikeList;
	}
	
	@Override
	public String toString() {
		return this.tweetMsg;
	}
}
