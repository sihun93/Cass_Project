/**
 * 
 */
package com.work.model.dto;

/**
 * @author 최아연
 * 포인트 상품 구매 내역 Dto
 */
public class PointBuyDto {
	private String buyNum;
	private String pboard_num;
	private String member_id;
	private String pboard_title;
	private String pboard_img;
	private int pboardCount;
	private int pboardPrice;
	private String buyDate;
	
	public PointBuyDto() {
	}

	

	/**
	 * @param buyNum
	 * @param pboard_num
	 * @param member_id
	 * @param pboard_title
	 * @param pboard_img
	 * @param pboardCount
	 * @param pboardPrice
	 */
	public PointBuyDto(String buyNum, String pboard_num, String member_id, String pboard_title, String pboard_img,
			int pboardCount, int pboardPrice) {
		super();
		this.buyNum = buyNum;
		this.pboard_num = pboard_num;
		this.member_id = member_id;
		this.pboard_title = pboard_title;
		this.pboard_img = pboard_img;
		this.pboardCount = pboardCount;
		this.pboardPrice = pboardPrice;
	}



	/**
	 * @return the buyDate
	 */
	public String getBuyDate() {
		return buyDate;
	}



	/**
	 * @param buyDate the buyDate to set
	 */
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}



	/**
	 * @return the pboardCount
	 */
	public int getPboardCount() {
		return pboardCount;
	}



	/**
	 * @param pboardCount the pboardCount to set
	 */
	public void setPboardCount(int pboardCount) {
		this.pboardCount = pboardCount;
	}



	/**
	 * @return the buyNum
	 */
	public String getBuyNum() {
		return buyNum;
	}

	/**
	 * @param buyNum the buyNum to set
	 */
	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	/**
	 * @return the pboard_num
	 */
	public String getPboard_num() {
		return pboard_num;
	}

	/**
	 * @param pboard_num the pboard_num to set
	 */
	public void setPboard_num(String pboard_num) {
		this.pboard_num = pboard_num;
	}

	/**
	 * @return the member_id
	 */
	public String getMember_id() {
		return member_id;
	}

	/**
	 * @param member_id the member_id to set
	 */
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	/**
	 * @return the pboard_title
	 */
	public String getPboard_title() {
		return pboard_title;
	}

	/**
	 * @param pboard_title the pboard_title to set
	 */
	public void setPboard_title(String pboard_title) {
		this.pboard_title = pboard_title;
	}

	/**
	 * @return the pboard_img
	 */
	public String getPboard_img() {
		return pboard_img;
	}

	/**
	 * @param pboard_img the pboard_img to set
	 */
	public void setPboard_img(String pboard_img) {
		this.pboard_img = pboard_img;
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
		builder.append("PointBuyDto [buyNum=");
		builder.append(buyNum);
		builder.append(", pboard_num=");
		builder.append(pboard_num);
		builder.append(", member_id=");
		builder.append(member_id);
		builder.append(", pboard_title=");
		builder.append(pboard_title);
		builder.append(", pboard_img=");
		builder.append(pboard_img);
		builder.append(", pboardCount=");
		builder.append(pboardCount);
		builder.append(", pboardPrice=");
		builder.append(pboardPrice);
		builder.append(", buyDate=");
		builder.append(buyDate);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
