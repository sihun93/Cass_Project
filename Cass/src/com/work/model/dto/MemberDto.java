/**
 * @author 백시훈
 */
package com.work.model.dto;

public class MemberDto extends MasterMemberDto {
	private String memberPw;
	private String memberAddr;
	private String memberEmail;
	private String memberMobile;
	private String memberBirth;
	private int point;
	private String sex;
	
	public MemberDto() {}

	
	
	
	public MemberDto(String memberId, String memberPw, String memberAddr, String memberEmail, String memberMobile, int point) {
		super(memberId);
		this.memberPw = memberPw;
		this.memberAddr = memberAddr;
		this.memberEmail = memberEmail;
		this.memberMobile = memberMobile;
		this.point = point;
	}




	/**
	 * @param memberId
	 * @param memberPw
	 * @param memberAddr
	 * @param memberEmail
	 * @param memberMobile
	 * @param memberBirth
	 * @param point
	 * @param grade
	 * @param sex
	 */
	public MemberDto(String memberId, String memberPw, String memberAddr, String memberEmail, String memberMobile,
			String memberBirth, String grade, int point, String sex) {
		super(memberId,grade);
		this.memberPw = memberPw;
		this.memberAddr = memberAddr;
		this.memberEmail = memberEmail;
		this.memberMobile = memberMobile;
		this.memberBirth = memberBirth;
		this.point = point;
		this.sex = sex;
	}


	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}

	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	/**
	 * @return the memberAddr
	 */
	public String getMemberAddr() {
		return memberAddr;
	}

	/**
	 * @param memberAddr the memberAddr to set
	 */
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	/**
	 * @return the memberEmail
	 */
	public String getMemberEmail() {
		return memberEmail;
	}

	/**
	 * @param memberEmail the memberEmail to set
	 */
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	/**
	 * @return the memberMobile
	 */
	public String getMemberMobile() {
		return memberMobile;
	}

	/**
	 * @param memberMobile the memberMobile to set
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	/**
	 * @return the memberBirth
	 */
	public String getMemberBirth() {
		return memberBirth;
	}

	/**
	 * @param memberBirth the memberBirth to set
	 */
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}




	@Override
	public String toString() {
		return  super.toString() +",memberPw=" + memberPw + ", memberAddr=" + memberAddr + ", memberEmail=" + memberEmail
				+ ", memberMobile=" + memberMobile + ", memberBirth=" + memberBirth + ", point=" + point + ", sex="
				+ sex;
	}

	
}
