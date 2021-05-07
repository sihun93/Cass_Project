package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.BusinessDao;
import com.work.model.dao.CommonException;
import com.work.model.dao.JdbcTemplate;
import com.work.model.dto.BusinessMemberDto;
import com.work.util.Utility;

public class BusinessBiz {

	private BusinessDao dao = BusinessDao.getInstance();
	
	
	/**
	 * 사업자로그인 biz
	 * @param dto
	 * @throws CommonException
	 */
	public void bsLogin(BusinessMemberDto dto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.businessLogin(con, dto);
		}catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	/**
	 * 사업자 회원가입
	 * @param dto
	 * @throws CommonException
	 */
	public void addBusiness(BusinessMemberDto dto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.addBusiness(con, dto);
		}catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	/**
	 * 사업자 내정보조회
	 * @param dto
	 * @throws CommonException
	 */
	public void businessInfo(BusinessMemberDto dto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.businessInfo(con, dto);
		} catch(CommonException e) {
			throw e;
		} finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	/**
	 * 사업자 내정보수정
	 * @param dto
	 * @throws CommonException
	 */
	public void bsUpdateInfo(BusinessMemberDto dto) throws CommonException {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.updateBusinessInfo(con, dto);
			JdbcTemplate.commit(con);
		}catch(CommonException e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	/**
	 * 사업자 전체조회
	 * @param list
	 * @throws CommonException
	 */
	public void businessList(ArrayList<BusinessMemberDto> list) throws CommonException {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.businessList(con, list);
		}catch(CommonException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	/**
	 * 사업자 아이디찾기
	 * @param businessNum
	 * @param businessPhone
	 * @return
	 */
	public String findBsId(String businessNum, String businessPhone) {
		String memberId = dao.findIdBusiness(businessNum, businessPhone);
		return memberId;
	}
	
	
	/**
	 * 사업자 비밀번호 찾기
	 * @param memberId
	 * @param businessPhone
	 * @return
	 */
	public String findBsPw(String memberId, String businessPhone) {
		boolean bPw = dao.findPwBusiness(memberId, businessPhone);
		if(!bPw) {
			return null;
		}
		String businessPw = Utility.getSecureNumberAndString(8);
		int result = dao.updatebusinessPw(memberId, businessPw);
		if(result == 1) {
			return businessPw;
		}
		return null;
	}
	
	/**
	 * 사업자 회원탈퇴
	 * @param memberId
	 * @return
	 */
	public int deleteBusiness(String memberId) {
		return dao.deleteBusiness(memberId);
	}
	
}
