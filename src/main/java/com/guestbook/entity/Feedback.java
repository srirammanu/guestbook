package com.guestbook.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name = "Feedback.findFeedbackForUser", query = "select s from Feedback s where s.createBy = ?1 and s.isRemoved in (?2)")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long feedbackId;

	@Column(nullable = true)
	private String feedbackTxt;

	@Column(nullable = true, length = 64)
	private String feedbackImg;

	@Column
	private boolean isFeedbackApproved;

	@Column(nullable = false)
	private String createBy;

	@Column(nullable = false)
	private Timestamp creteTs;

	@Column
	private boolean isRemoved;

	public Feedback() {

	}

	public Feedback(String feedbackTxt, String feedbackImg, boolean isFeedbackApproved, String createBy,
			Timestamp creteTs, boolean isRemoved) {
		super();
		this.feedbackTxt = feedbackTxt;
		this.feedbackImg = feedbackImg;
		this.isFeedbackApproved = isFeedbackApproved;
		this.createBy = createBy;
		this.creteTs = creteTs;
		this.isRemoved = isRemoved;
	}

	public boolean isFeedbackApproved() {
		return isFeedbackApproved;
	}

	public void setFeedbackApproved(boolean isFeedbackApproved) {
		this.isFeedbackApproved = isFeedbackApproved;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedbackTxt=" + feedbackTxt + ", feedbackImg=" + feedbackImg
				+ ", isFeedbackApproved=" + isFeedbackApproved + ", createBy=" + createBy + ", creteTs=" + creteTs
				+ ", isRemoved=" + isRemoved + "]";
	}

}
