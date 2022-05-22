package com.guestbook.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name = "Feedback.findFeedbackForUser", query = "select s from Feedback s where s.userId = :userId")

public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long feedbackId;

	@Column(nullable = true)
	private String feedbackTxt;

	@Column( name = "fileimage" )
	@Lob
	private byte[] fileimage;
	
	@Column(nullable = true, length = 64)
	private String feedbackImg;

	@Column
	private boolean isFeedbackApproved;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User userId;

	@Column(nullable = false)
	private Timestamp creteTs;

	@Column
	private boolean isRemoved;
	
	public Feedback() {

	}

	public Feedback(String feedbackTxt, String feedbackImg, boolean isFeedbackApproved, User userId,
			Timestamp creteTs, boolean isRemoved) {
		super();
		this.feedbackTxt = feedbackTxt;
		this.feedbackImg = feedbackImg;
		this.isFeedbackApproved = isFeedbackApproved;
		this.setUserId(userId);
		this.creteTs = creteTs;
		this.isRemoved = isRemoved;
	}

	public boolean isFeedbackApproved() {
		return isFeedbackApproved;
	}

	public void setFeedbackApproved(boolean isFeedbackApproved) {
		this.isFeedbackApproved = isFeedbackApproved;
	}

	public Timestamp getCreteTs() {
		return creteTs;
	}

	public void setCreteTs(Timestamp creteTs) {
		this.creteTs = creteTs;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

	public String getFeedbackTxt() {
		return feedbackTxt;
	}

	public void setFeedbackTxt(String feedbackTxt) {
		this.feedbackTxt = feedbackTxt;
	}

	public String getFeedbackImg() {
		return feedbackImg;
	}

	public void setFeedbackImg(String feedbackImg) {
		this.feedbackImg = feedbackImg;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedbackTxt=" + feedbackTxt + ", feedbackImg=" + feedbackImg
				+ ", isFeedbackApproved=" + isFeedbackApproved + ", userId=" + getUserId() + ", creteTs=" + creteTs
				+ ", isRemoved=" + isRemoved + "]";
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public byte[] getFileimage() {
		return fileimage;
	}

	public void setFileimage(byte[] fileimage) {
		this.fileimage = fileimage;
	}

}
