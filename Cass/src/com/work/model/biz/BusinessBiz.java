package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.BusinessDao;
import com.work.model.dao.CommonException;
import com.work.model.dao.JdbcTemplate;
import com.work.model.dto.BusinessMemberDto;
import com.work.util.Utility;

public class BusinessBiz {

	private BusinessDao bdao = BusinessDao.getInstance();
	
	
	/**
	 * 사업자로그인 biz
	 * @param bdto
	 * @throws CommonException
	 */
	public void bsLogin(BusinessMemberDto bdto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			bdao.businessLogin(con, bdto);
		}catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	/**
	 * 사업자 회원가입
	 * @param bdto
	 * @throws CommonException
	 */
	public void addBusiness(BusinessMemberDto bdto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			bdao.addBusiness(con, bdto);
		}catch(CommonException e) {
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	/**
	 * 사업자 내정보조회
	 * @param bdto
	 * @throws CommonException
	 */
	public void businessInfo(BusinessMemberDto bdto) throws CommonException{
		Connection con = JdbcTemplate.getConnection();
		try {
			bdao.businessInfo(con, bdto);
		} catch(CommonException e) {
			throw e;
		} finally {
			JdbcTemplate.close(con);
		}
	}
	
	
	/**
	 * 사업자 내정보수정
	 * @param bdto
	 * @throws CommonException
	 */
	public void bsUpdateInfo(BusinessMemberDto bdto) throws CommonException {
		Connection con = JdbcTemplate.getConnection();
		try {
			bdao.updateBusinessInfo(con, bdto);
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
			bdao.businessList(con, list);
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
		String businessId = bdao.findIdBusiness(businessNum, businessPhone);
		return businessId;
	}
	
	
	/**
	 * 사업자 비밀번호 찾기
	 * @param businessId
	 * @param businessPhone
	 * @return
	 */
	public String findBsPw(String businessId, String businessPhone) {
		boolean bPw = bdao.findPwBusiness(businessId, businessPhone);
		if(!bPw) {
			return null;
		}
		String businessPw = Utility.getSecureNumberAndString(8);
		int result = bdao.updatebusinessPw(businessId, businessPw);
		if(result == 1) {
			return businessPw;
		}
		return null;
	}
	
	/**
	 * 사업자 회원탈퇴
	 * @param businessId
	 * @return
	 */
	public int deleteBusiness(String businessId) {
		return bdao.deleteBusiness(businessId);
	}
	
}
