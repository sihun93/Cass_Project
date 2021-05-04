
/**
 * 
 */
package com.work.model.dto;

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
	/** 회사 이미지*/
	private String mboardImg;
	/** 회사 정보*/
	private String mboardInfo;
	/** 메인서비스 내용*/
	private String mboardContent;
	
	public MainBoardDto() {
	}
	
	public MainBoardDto(String mboardNum, String businessId, String mcategoryNum, String scategoryNum,
			String mboardTitle, int mboardScore, String mboardImg, String mboardInfo, String mboardContent) {
		super();
		this.mboardNum = mboardNum;
		this.businessId = businessId;
		this.mcategoryNum = mcategoryNum;
		this.scategoryNum = scategoryNum;
		this.mboardTitle = mboardTitle;
		this.mboardScore = mboardScore;
		this.mboardImg = mboardImg;
		this.mboardInfo = mboardInfo;
		this.mboardContent = mboardContent;
	}

	public String getMboardNum() {
		return mboardNum;
	}

	public void setMboardNum(String mboardNum) {
		this.mboardNum = mboardNum;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getMcategoryNum() {
		return mcategoryNum;
	}

	public void setMcategoryNum(String mcategoryNum) {
		this.mcategoryNum = mcategoryNum;
	}

	public String getScategoryNum() {
		return scategoryNum;
	}

	public void setScategoryNum(String scategoryNum) {
		this.scategoryNum = scategoryNum;
	}

	public String getMboardTitle() {
		return mboardTitle;
	}

	public void setMboardTitle(String mboardTitle) {
		this.mboardTitle = mboardTitle;
	}

	public int getMboardScore() {
		return mboardScore;
	}

	public void setMboardScore(int mboardScore) {
		this.mboardScore = mboardScore;
	}

	public String getMboardImg() {
		return mboardImg;
	}

	public void setMboardImg(String mboardImg) {
		this.mboardImg = mboardImg;
	}

	public String getMboardInfo() {
		return mboardInfo;
	}

	public void setMboardInfo(String mboardInfo) {
		this.mboardInfo = mboardInfo;
	}

	public String getMboardContent() {
		return mboardContent;
	}

	public void setMboardContent(String mboardContent) {
		this.mboardContent = mboardContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MainBoardDto [mboardNum=");
		builder.append(mboardNum);
		builder.append("\n, businessId=");
		builder.append(businessId);
		builder.append("\n, mcategoryNum=");
		builder.append(mcategoryNum);
		builder.append("\n, scategoryNum=");
		builder.append(scategoryNum);
		builder.append("\n, mboardTitle=");
		builder.append(mboardTitle);
		builder.append("\n, mboardScore=");
		builder.append(mboardScore);
		builder.append("\n, mboardImg=");
		builder.append(mboardImg);
		builder.append("\n, mboardInfo=");
		builder.append(mboardInfo);
		builder.append("\n, mboardContent=");
		builder.append(mboardContent);
		builder.append("]");
		return builder.toString();
	}


	
	
	
}
