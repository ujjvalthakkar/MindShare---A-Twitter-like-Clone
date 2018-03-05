package com.neu.ujjval.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hashtags")
public class HashTags {

	public HashTags() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "hashtagId", unique=true, nullable = false)
	private long hashtagId;
	
	@Column(name = "hashtag")
	private String hashtag;
}