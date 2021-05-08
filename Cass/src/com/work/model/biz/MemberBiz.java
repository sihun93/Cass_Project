package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

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
			dao.addData(con, dto);
		} catch(CommonException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	
	/**
	 * 내 정보 조회 biz
	 * 
	 */
	public void myInfo(MemberDto dto) throws CommonException {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.myInfo(con, dto);
		}catch(CommonException e){
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	
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
	public String findId(String memberBirth, String memberMobile) {
		String memberId = dao.findIdMember(memberBirth, memberMobile);
		return memberId;
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
		return null;
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
	 * 사업자회원삭제
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		return dao.deleteMember(memberId);
	}
	
	
	/**
	 * 구매후 포인트 변경 (아연)
	 * @param memberId
	 * @param memberPoint
	 */
	public void updatepoint(String memberId, int memberPoint) {
		Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.updatepoint(con, memberId, memberPoint);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}



	/**
	 * 회원 포인트 가져오기
	 * @param memberId
	 * @return
	 */
	public MemberDto getPoint(String memberId) {
		return dao.selectPoint(memberId);
	}
	
	/**
	 * 포인트 변경
	 * @param dto
	 */
	public void pointModify(MemberDto dto) throws Exception {
		dao.pointModify(dto);
	}
	/** 매월 1일 데이터 초기화 */
	public void timeBreaker() {

        Calendar cal = Calendar.getInstance();
        String[] date = Utility.getCurrentDate().split("-");
        cal.set(Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]));
        if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) == Integer.parseInt(date[2])) {
        	dao.timeT();
        }
        if(Integer.parseInt(date[2]) == 1) {
        	dao.timeN();
        }
        
	}
	
}
