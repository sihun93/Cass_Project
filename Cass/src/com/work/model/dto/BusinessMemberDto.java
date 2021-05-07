/**
 * 
 */
package com.work.model.dto;

/**
 * @author 김성재
 *
 */
public class BusinessMemberDto {
	/** 아이디 */
	private String	businessId;
	/** 비밀번호 */
	private String	businessPw;
	/** 사업자 번호 */
	private String	businessNum;
	/** 상호명 */
	private String	businessTitle;
	/** 사업자 주소 */
	private String	businessAddr;
	/** 전화번호 */
	private String	businessPhone;
	/** 사업자 홈페이지 */
	private String	businessHomepage;
	
	public BusinessMemberDto() {
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
	 * @return the businessPw
	 */
	public String getBusinessPw() {
		return businessPw;
	}

	/**
	 * @param businessPw the businessPw to set
	 */
	public void setBusinessPw(String businessPw) {
		this.businessPw = businessPw;
	}

	/**
	 * @return the businessNum
	 */
	public String getBusinessNum() {
		return businessNum;
	}

	/**
	 * @param businessNum the businessNum to set
	 */
	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	/**
	 * @return the businessTitle
	 */
	public String getBusinessTitle() {
		return businessTitle;
	}

	/**
	 * @param businessTitle the businessTitle to set
	 */
	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	/**
	 * @return the businessAddr
	 */
	public String getBusinessAddr() {
		return businessAddr;
	}

	/**
	 * @param businessAddr the businessAddr to set
	 */
	public void setBusinessAddr(String businessAddr) {
		this.businessAddr = businessAddr;
	}

	/**
	 * @return the businessPhone
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * @param businessPhone the businessPhone to set
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
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
	 * @param businessId
	 * @param businessPw
	 * @param businessNum
	 * @param businessTitle
	 * @param businessAddr
	 * @param businessPhone
	 * @param businessHomepage
	 */
	public BusinessMemberDto(String businessId, String businessPw, String businessNum, String businessTitle,
			String businessAddr, String businessPhone, String businessHomepage) {
		super();
		this.businessId = businessId;
		this.businessPw = businessPw;
		this.businessNum = businessNum;
		this.businessTitle = businessTitle;
		this.businessAddr = businessAddr;
		this.businessPhone = businessPhone;
		this.businessHomepage = businessHomepage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[businessId=");
		builder.append(businessId);
		builder.append(", businessPw=");
		builder.append(businessPw);
		builder.append(", businessNum=");
		builder.append(businessNum);
		builder.append(", businessTitle=");
		builder.append(businessTitle);
		builder.append(", businessAddr=");
		builder.append(businessAddr);
		builder.append(", businessPhone=");
		builder.append(businessPhone);
		builder.append(", businessHomepage=");
		builder.append(businessHomepage);
		builder.append("]");
		return builder.toString();
	}
	
}
