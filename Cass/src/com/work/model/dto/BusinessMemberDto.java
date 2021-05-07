/**
 * 
 */
package com.work.model.dto;

/**
 * @author 김성재
 *
 */
public class BusinessMemberDto  extends MasterMemberDto{
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
	
	public BusinessMemberDto(String memberId, String businessPw, String businessNum, String businessTitle,
			String businessAddr, String businessPhone, String businessHomepage) {
		super(memberId);
		this.businessPw = businessPw;
		this.businessNum = businessNum;
		this.businessTitle = businessTitle;
		this.businessAddr = businessAddr;
		this.businessPhone = businessPhone;
		this.businessHomepage = businessHomepage;
	}

	public String getBusinessPw() {
		return businessPw;
	}

	public void setBusinessPw(String businessPw) {
		this.businessPw = businessPw;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public String getBusinessAddr() {
		return businessAddr;
	}

	public void setBusinessAddr(String businessAddr) {
		this.businessAddr = businessAddr;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessHomepage() {
		return businessHomepage;
	}

	public void setBusinessHomepage(String businessHomepage) {
		this.businessHomepage = businessHomepage;
	}

	@Override
	public String toString() {
		return super.toString()+", businessPw=" + businessPw + ", businessNum=" + businessNum + ", businessTitle=" + businessTitle
				+ ", businessAddr=" + businessAddr + ", businessPhone=" + businessPhone + ", businessHomepage="
				+ businessHomepage;
	}
	
	

	
}
