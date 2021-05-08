package com.work.model.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageEntity {
	private HashMap<String, ArrayList<String>> messageList = new HashMap<String, ArrayList<String>>();
	private String url;
	private String linkTitle;
	private String type;
	private int index;
	
	public MessageEntity() {

		ArrayList<String> error = new ArrayList<String>();
		error.add("모든 항목을 제대로 입력해주세요");			// 0
		error.add("모든 항목을 제대로 입력해주세요");		// 1
		error.add("아이디 또는 비밀번호가 다릅니다");			// 2
		error.add("아이디 또는 비밀번호가 다릅니다");		// 3
		error.add("회원 정보 조회 오류");		// 4
		error.add("사업자 회원 정보 조회 오류");		// 5
		error.add("회원 정보 수정 오류");	// 6
		error.add("사업자 정보 수정 오류");	// 7
		error.add("회원 게시글 작성 오류");			// 8
		error.add("사업장 소개글 등록 오류");			// 9
		error.add("리뷰글 등록 오류");			// 10
		error.add("Q&A 게시글 등록 오류");			// 11
		error.add("전체회원 조회 오류");			// 12
		error.add("찾으려는 아이디의 정보를 정확히 입력하세요");	//13
		error.add("찾으려는 비밀번호의 정보를 정확히 입력하세요"); //14
		error.add("회원삭제 오류"); //15
		error.add("포인트 수정 오류"); //16
		
		ArrayList<String> validation = new ArrayList<String>();
		validation.add("아이디 정보 오류");		// 0
		validation.add("비밀번호 정보 오류");		// 1
		validation.add("주소 정보 오류");		// 2
		validation.add("사업자 코드 정보 오류");		// 3
		validation.add("생년월일 또는 휴대번호를 잘못 입력하셨습니다.");		// 4
		validation.add("아이디 또는 휴대번호를 잘못 입력하셨습니다.");		// 5
		validation.add("사업자번호 또는 휴대번호를 잘못 입력하셨습니다.");		// 6
		validation.add("아이디 또는 휴대번호를 잘못 입력하셨습니다.");		// 7
		
		ArrayList<String> success = new ArrayList<String>();
		success.add("회원 등록 성공");	// 0
		success.add("회원 로그인 성공");		// 1
		success.add("회원정보 수정 성공");	// 2
		success.add("회원 정보 수정 성공");		// 3
		success.add("로그아웃 성공");		// 4
		success.add("리뷰 작성 성공");		// 5
		success.add("Q&A 작성 성공");		// 6
		success.add("아이디 찾기 성공");		// 7
		success.add("비밀번호 찾기 성공");	 // 8
		success.add("포인트 수정 성공"); // 9
		
		
		ArrayList<String> message = new ArrayList<String>();
		message.add("이 페이지는 로그인이 필요합니다.");
		
		messageList.put("error", error);
		messageList.put("validation", validation);
		messageList.put("success", success);
		messageList.put("message", message);
	}
	
	public MessageEntity(String type, int index) {
		this();
		
		this.type = type;
		this.index = index;
	}

	public String getMessage() {
		return messageList.get(type).get(index);
	}

	public String getUrl() {
		return url;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public String getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

}
