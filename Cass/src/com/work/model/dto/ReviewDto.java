/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * @author qorwl
 *
 */
public class ReviewDto {
	private int reviewNum;
	private String mboardNum;
	private String memberId;
	private int score;
	private String reviewContent;
	private String reviewImg;
	private String reviewDate;
	
	public ReviewDto() {
	}

	/**
	 * @return the reviewNum
	 */
	public int getReviewNum() {
		return reviewNum;
	}

	/**
	 * @param reviewNum the reviewNum to set
	 */
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	/**
	 * @return the mboardNum
	 */
	public String getMboardNum() {
		return mboardNum;
	}

	/**
	 * @param mboardNum the mboardNum to set
	 */
	public void setMboardNum(String mboardNum) {
		this.mboardNum = mboardNum;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the reviewContent
	 */
	public String getReviewContent() {
		return reviewContent;
	}

	/**
	 * @param reviewContent the reviewContent to set
	 */
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	/**
	 * @return the reviewImg
	 */
	public String getReviewImg() {
		return reviewImg;
	}

	/**
	 * @param reviewImg the reviewImg to set
	 */
	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}

	/**
	 * @return the reviewDate
	 */
	public String getReviewDate() {
		return reviewDate;
	}

	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @param reviewNum
	 * @param mboardNum
	 * @param memberId
	 * @param score
	 * @param reviewContent
	 * @param reviewImg
	 * @param reviewDate
	 */
	public ReviewDto(int reviewNum, String mboardNum, String memberId, int score, String reviewContent,
			String reviewImg, String reviewDate) {
		super();
		this.reviewNum = reviewNum;
		this.mboardNum = mboardNum;
		this.memberId = memberId;
		this.score = score;
		this.reviewContent = reviewContent;
		this.reviewImg = reviewImg;
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[reviewNum=");
		builder.append(reviewNum);
		builder.append(", mboardNum=");
		builder.append(mboardNum);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append(", score=");
		builder.append(score);
		builder.append(", reviewContent=");
		builder.append(reviewContent);
		builder.append(", reviewImg=");
		builder.append(reviewImg);
		builder.append(", reviewDate=");
		builder.append(reviewDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
