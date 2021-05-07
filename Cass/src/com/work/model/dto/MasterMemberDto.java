package com.work.model.dto;

public class MasterMemberDto {
	private String memberId;
	private String grade;
	
	public MasterMemberDto() {
	}
	
	public MasterMemberDto(String memberId) {
		this.memberId = memberId;
	}
	public MasterMemberDto(String memberId, String grade) {
		this.memberId = memberId;
		this.grade =grade;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "memberId=" + memberId + ", grade=" + grade;
	}
	
	
	
}
