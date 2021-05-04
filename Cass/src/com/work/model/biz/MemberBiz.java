package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.CommonException;
import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.MemberDao;
import com.work.model.dto.MemberDto;
import com.work.util.Utility;

public class MemberBiz {

	private MemberDao dao = MemberDao.getInstance();
	
	
	/**
	 * 로그인biz
	 * @param dto
	 * @throws CommonException
	 */
	public void login(MemberDto dto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.memberLogin(con, dto);
		}catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	
	
	/**
	 * 회원가입 biz
	 * @param dto
	 * @throws CommonException
	 */
	public void addMember(MemberDto dto) throws CommonException {
		Connection con = JdbcTemplate.getConnection();
		dto.setGrade("G");
		dto.setPoint(1000);
		try {
			dao.addMember(con, dto);
		} catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	
	/**
	 * 내 정보 조회 biz
	 * 
	 */
	public MemberDto myInfo(String memberId) {
		return dao.myInfo(memberId);
	}
	/*
	 * public void myInfo(MemberDto dto) throws CommonException{ Connection con =
	 * JdbcTemplate.getConnection(); try { dao.myInfo(con, dto);
	 * }catch(CommonException e) { throw e; }finally { JdbcTemplate.close(con); } }
	 */
	
	
	/**
	 * 내 정보 수정 biz
	 * @param dto
	 * @throws CommonException
	 */
	public void updateInfo(MemberDto dto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.updateMyInfo(con, dto);
			JdbcTemplate.commit(con);
		} catch(CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	
	
	/**
	 * 회원 아이디찾기 biz
	 * @param memberBirth
	 * @param memberMobile
	 * @return
	 */	
	public void findId(MemberDto dto) {
		dao.findIdMember(dto);
	}
	
	
	
	/**
	 * 회원 비밀번호 찾기 biz
	 * @param memberId
	 * @param memberMobile
	 * @return
	 */
	public String findPw(String memberId, String memberMobile) {
		boolean mPw = dao.findPwMember(memberId, memberMobile);
		if(!mPw) {
			return null;
		}
		String memberPw = Utility.getSecureNumberAndString(8);
		int result = dao.updateMemberPw(memberId, memberPw);
		if(result == 1) {
			return memberPw;
		}
		return memberPw;
	}
	
	
	
	
	/**
	 * 전체회원조회 biz
	 * @param list
	 * @throws CommonException
	 */
	public void memberList(ArrayList<MemberDto> list) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.memberList(con, list);
		}catch(CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	/**
	 * 회원 탈퇴 biz
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		dao.deleteBoard(memberId);
		return dao.deleteMember(memberId);
	}
	
	
}
