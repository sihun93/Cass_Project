/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * @author 김성재
 *
 */
public class MainBoardDto {
	/** 메인 게시판 번호*/
	private String mboardNum;
	/** 사업자 아이디(작성자 아이디)*/
	private String businessId;
	/** 메인 카테고리 번호*/
	private String mcategoryNum;
	/** 서브 카테고리 번호*/
	private String scategoryNum;
	/** 제목*/
	private String	mboardTitle;
	/** 평점*/
	private int	mboardScore;
	/** 내용*/
	private StringBuffer mboardContent;
	/** 사업자 홈페이지*/
	private String businessHomepage;
	/** 이미지 주소 저장*/
	private ArrayList<String> mboardImg;
	/** 회사 소개*/
	private String	mboardInfo;
	public MainBoardDto() {
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
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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
	 * @return the scategoryNum
	 */
	public String getScategoryNum() {
		return scategoryNum;
	}
	/**
	 * @param scategoryNum the scategoryNum to set
	 */
	public void setScategoryNum(String scategoryNum) {
		this.scategoryNum = scategoryNum;
	}
	/**
	 * @return the mboardTitle
	 */
	public String getMboardTitle() {
		return mboardTitle;
	}
	/**
	 * @param mboardTitle the mboardTitle to set
	 */
	public void setMboardTitle(String mboardTitle) {
		this.mboardTitle = mboardTitle;
	}
	/**
	 * @return the mboardScore
	 */
	public int getMboardScore() {
		return mboardScore;
	}
	/**
	 * @param mboardScore the mboardScore to set
	 */
	public void setMboardScore(int mboardScore) {
		this.mboardScore = mboardScore;
	}
	/**
	 * @return the mboardContent
	 */
	public StringBuffer getMboardContent() {
		return mboardContent;
	}
	/**
	 * @param mboardContent the mboardContent to set
	 */
	public void setMboardContent(StringBuffer mboardContent) {
		this.mboardContent = mboardContent;
	}
	/**
	 * @return the businessHomepage
	 */
	public String getBusinessHomepage() {
		return businessHomepage;
	}
	/**
	 * @param businessHomepage the businessHomepage to set
	 */
	public void setBusinessHomepage(String businessHomepage) {
		this.businessHomepage = businessHomepage;
	}
	/**
	 * @return the mboardImg
	 */
	public ArrayList<String> getMboardImg() {
		return mboardImg;
	}
	/**
	 * @param mboardImg the mboardImg to set
	 */
	public void setMboardImg(ArrayList<String> mboardImg) {
		this.mboardImg = mboardImg;
	}
	/**
	 * @return the mboardInfo
	 */
	public String getMboardInfo() {
		return mboardInfo;
	}
	/**
	 * @param mboardInfo the mboardInfo to set
	 */
	public void setMboardInfo(String mboardInfo) {
		this.mboardInfo = mboardInfo;
	}
	/**
	 * @param mboardNum
	 * @param businessId
	 * @param mcategoryNum
	 * @param scategoryNum
	 * @param mboardTitle
	 * @param mboardScore
	 * @param mboardContent
	 * @param businessHomepage
	 * @param mboardImg
	 * @param mboardInfo
	 */
	public MainBoardDto(String mboardNum, String businessId, String mcategoryNum, String scategoryNum,
			String mboardTitle, int mboardScore, StringBuffer mboardContent, String businessHomepage,
			ArrayList<String> mboardImg, String mboardInfo) {
		super();
		this.mboardNum = mboardNum;
		this.businessId = businessId;
		this.mcategoryNum = mcategoryNum;
		this.scategoryNum = scategoryNum;
		this.mboardTitle = mboardTitle;
		this.mboardScore = mboardScore;
		this.mboardContent = mboardContent;
		this.businessHomepage = businessHomepage;
		this.mboardImg = mboardImg;
		this.mboardInfo = mboardInfo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[mboardNum=");
		builder.append(mboardNum);
		builder.append(", businessId=");
		builder.append(businessId);
		builder.append(", mcategoryNum=");
		builder.append(mcategoryNum);
		builder.append(", scategoryNum=");
		builder.append(scategoryNum);
		builder.append(", mboardTitle=");
		builder.append(mboardTitle);
		builder.append(", mboardScore=");
		builder.append(mboardScore);
		builder.append(", mboardContent=");
		builder.append(mboardContent);
		builder.append(", businessHomepage=");
		builder.append(businessHomepage);
		builder.append(", mboardImg=");
		builder.append(mboardImg);
		builder.append(", mboardInfo=");
		builder.append(mboardInfo);
		builder.append("]");
		return builder.toString();
	}

}
