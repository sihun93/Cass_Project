/**
 * 
 */
package com.work.model.dto;

/**
 * @author 백시훈
 *
 */
public class PointDto {
	private String pboardNum;
	private String mcategoryNum;
	private String pboardTitle;
	private String pboardImg;
	private String pboardContent;
	private int pboardPrice;

	public PointDto() {
	}

	/**
	 * @param pboardNum
	 * @param mcategoryNum
	 * @param pboardTitle
	 * @param pboardImg
	 * @param pboardContent
	 * @param pboardPrice
	 */
	public PointDto(String pboardNum, String mcategoryNum, String pboardTitle, String pboardImg, String pboardContent,
			int pboardPrice) {
		super();
		this.pboardNum = pboardNum;
		this.mcategoryNum = mcategoryNum;
		this.pboardTitle = pboardTitle;
		this.pboardImg = pboardImg;
		this.pboardContent = pboardContent;
		this.pboardPrice = pboardPrice;
	}

	/**
	 * @return the pboardNum
	 */
	public String getPboardNum() {
		return pboardNum;
	}

	/**
	 * @param pboardNum the pboardNum to set
	 */
	public void setPboardNum(String pboardNum) {
		this.pboardNum = pboardNum;
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
	 * @return the pboardTitle
	 */
	public String getPboardTitle() {
		return pboardTitle;
	}

	/**
	 * @param pboardTitle the pboardTitle to set
	 */
	public void setPboardTitle(String pboardTitle) {
		this.pboardTitle = pboardTitle;
	}

	/**
	 * @return the pboardImg
	 */
	public String getPboardImg() {
		return pboardImg;
	}

	/**
	 * @param pboardImg the pboardImg to set
	 */
	public void setPboardImg(String pboardImg) {
		this.pboardImg = pboardImg;
	}

	/**
	 * @return the pboardContent
	 */
	public String getPboardContent() {
		return pboardContent;
	}

	/**
	 * @param pboardContent the pboardContent to set
	 */
	public void setPboardContent(String pboardContent) {
		this.pboardContent = pboardContent;
	}

	/**
	 * @return the pboardPrice
	 */
	public int getPboardPrice() {
		return pboardPrice;
	}

	/**
	 * @param pboardPrice the pboardPrice to set
	 */
	public void setPboardPrice(int pboardPrice) {
		this.pboardPrice = pboardPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PointDto [pboardNum=");
		builder.append(pboardNum);
		builder.append(", mcategoryNum=");
		builder.append(mcategoryNum);
		builder.append(", pboardTitle=");
		builder.append(pboardTitle);
		builder.append(", pboardImg=");
		builder.append(pboardImg);
		builder.append(", pboardContent=");
		builder.append(pboardContent);
		builder.append(", pboardPrice=");
		builder.append(pboardPrice);
		builder.append("]");
		return builder.toString();
	}

}
