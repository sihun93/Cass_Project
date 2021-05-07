/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * @author 백시훈
 */
public class QboardDto {
	private String qboardNum;
	private String qboardTitle;
	private String qboardContent;
	private String qboardDate;
	private String qboardImg;
	private String mcategoryNum;
	private String memberId;
	public QboardDto() {
	}
	/**
	 * @param qboardNum
	 * @param qboardTitle
	 * @param qboardContent
	 * @param qboardDate
	 * @param qboardImg
	 * @param mcategoryNum
	 * @param memberId
	 */
	public QboardDto(String qboardNum, String qboardTitle, String qboardContent, String qboardDate,
			String qboardImg, String mcategoryNum, String memberId) {
		super();
		this.qboardNum = qboardNum;
		this.qboardTitle = qboardTitle;
		this.qboardContent = qboardContent;
		this.qboardDate = qboardDate;
		this.qboardImg = qboardImg;
		this.mcategoryNum = mcategoryNum;
		this.memberId = memberId;
	}
	
	/**
	 * @return the qboardNum
	 */
	public String getQboardNum() {
		return qboardNum;
	}
	/**
	 * @param qboardNum the qboardNum to set
	 */
	public void setQboardNum(String qboardNum) {
		this.qboardNum = qboardNum;
	}
	/**
	 * @return the qboardTitle
	 */
	public String getQboardTitle() {
		return qboardTitle;
	}
	/**
	 * @param qboardTitle the qboardTitle to set
	 */
	public void setQboardTitle(String qboardTitle) {
		this.qboardTitle = qboardTitle;
	}
	/**
	 * @return the qboardContent
	 */
	public String getQboardContent() {
		return qboardContent;
	}
	/**
	 * @param qboardContent the qboardContent to set
	 */
	public void setQboardContent(String qboardContent) {
		this.qboardContent = qboardContent;
	}
	/**
	 * @return the qboardDate
	 */
	public String getQboardDate() {
		return qboardDate;
	}
	/**
	 * @param qboardDate the qboardDate to set
	 */
	public void setQboardDate(String qboardDate) {
		this.qboardDate = qboardDate;
	}
	/**
	 * @return the qboardImg
	 */
	public String getQboardImg() {
		return qboardImg;
	}
	/**
	 * @param qboardImg the qboardImg to set
	 */
	public void setQboardImg(String qboardImg) {
		this.qboardImg = qboardImg;
	}
	/**
	 * @return the mcategoryNum
	 */
	public String getMcategoryNum() {
		return mcategoryNum;
	}
	/**
	 * @param mcategoryNum the mcategoryNum to set
	 */
	public void setMcategoryNum(String mcategoryNum) {
		this.mcategoryNum = mcategoryNum;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[qboardNum=");
		builder.append(qboardNum);
		builder.append(", qboardTitle=");
		builder.append(qboardTitle);
		builder.append(", qboardContent=");
		builder.append(qboardContent);
		builder.append(", qboardDate=");
		builder.append(qboardDate);
		builder.append(", qboardImg=");
		builder.append(qboardImg);
		builder.append(", mcategoryNum=");
		builder.append(mcategoryNum);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
