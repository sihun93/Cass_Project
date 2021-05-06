/**
 * 
 */
package com.work.model.biz;

import java.util.ArrayList;

import com.work.model.dao.QboardDao;
import com.work.model.dto.AboardDto;
import com.work.model.dto.QboardDto;

/**
 * @author 박민주
 *
 */
public class QboardBiz {
	private QboardDao dao = QboardDao.getInstance();
	/** Q&A 게시글 전체 조회*/
	public  ArrayList<QboardDto> getQboardList() {
		return dao.getQboardList();
	}
	
	/** Q&A 게시글 상세 조회*/
	public  ArrayList<QboardDto> getQboardDetail(String qboardNum) {
		return dao.getQboardDetail(qboardNum);
	}
	
	/** Q&A 답글 전체 조회*/
	public  ArrayList<AboardDto> getAboardList(String qboardNum) {
		return dao.getAboardList(qboardNum);
	}
	
	/** Q&A 답글 등록*/
	public ArrayList<AboardDto> addAboard(String qboardNum, String aboardContent){
		return dao.addAboard(qboardNum, aboardContent);
	}
	
	/** Q&A 게시글 등록*/
	public int addQboard(String qboardTitle, String qboardContent, String qboardImg, String memberId) {
		return dao.addQboard(qboardTitle, qboardContent, qboardImg, memberId);
	}
	
	/** Q&A 게시글 삭제*/
	public int deleteQboard(String qboardNum) {
		return dao.deleteQboard(qboardNum);
	}
	
	/** Q&A 게시글 전체 조회(검색-내용+제목)*/
	public ArrayList<QboardDto> getQboardByA(String txtKeyWord) {
		return dao.getQboardByA(txtKeyWord);
	}
	
	/** Q&A 게시글 전체 조회(검색-제목)*/
	public ArrayList<QboardDto> getQboardByT(String txtKeyWord) {
		return dao.getQboardByT(txtKeyWord);
	}
	
	/** Q&A 게시글 전체 조회(검색-내용)*/
	public ArrayList<QboardDto> getQboardByC(String txtKeyWord) {
		return  dao.getQboardByC(txtKeyWord);
	}

	/** Q&A 게시글 수정*/
	public int updateQboard(String qboardNum, String qboardTitle, String qboardContent, String qboardImg) {
		return dao.updateQboard(qboardNum, qboardTitle, qboardContent, qboardImg);
	}

}
