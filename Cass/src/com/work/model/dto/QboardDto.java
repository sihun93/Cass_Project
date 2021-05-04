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
	private StringBuffer qboardContent;
	private String qboardDate;
	private ArrayList<String> qboardImg;
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
	public QboardDto(String qboardNum, String qboardTitle, StringBuffer qboardContent, String qboardDate,
			ArrayList<String> qboardImg, String mcategoryNum, String memberId) {
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
	public StringBuffer getQboardContent() {
		return qboardContent;
	}
	/**
	 * @param qboardContent the qboardContent to set
	 */
	public void setQboardContent(StringBuffer qboardContent) {
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
	public ArrayList<String> getQboardImg() {
		return qboardImg;
	}
	/**
	 * @param qboardImg the qboardImg to set
	 */
	public void setQboardImg(ArrayList<String> qboardImg) {
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
