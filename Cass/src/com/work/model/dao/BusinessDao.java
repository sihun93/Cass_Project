package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MessageEntity;

public class BusinessDao {
	
	private static BusinessDao instance = new BusinessDao();
	
	private BusinessDao() {}
	
	public static BusinessDao getInstance() {
		return instance;
	}
	
	/**
	 * 사업자회원 로그인
	 * @param con
	 * @param bdto
	 * @throws CommonException
	 */
	public void businessLogin(Connection con, BusinessMemberDto bdto) throws CommonException{
		String sql = "select * from BUSINESS where business_Id = ? and business_Pw = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getMemberId());
			pstmt.setString(2, bdto.getBusinessPw());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bdto.setMemberId(rs.getString("business_Id"));
				bdto.setBusinessPw(rs.getString("business_Pw"));
				bdto.setBusinessNum(rs.getString("business_Num"));
				bdto.setBusinessTitle(rs.getString("business_Title"));
				bdto.setBusinessAddr(rs.getString("business_Addr"));
				bdto.setBusinessPhone(rs.getString("business_Phone"));
				bdto.setBusinessHomepage(rs.getString("business_Homepage"));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	
	/**
	 * 사업자 회원가입 메서드
	 * @param con
	 * @param bdto
	 * @throws CommonException
	 */
	public void addBusiness(Connection con, BusinessMemberDto bdto) throws CommonException{
		String sql = "insert into BUSINESS VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getMemberId());
			pstmt.setString(2, bdto.getBusinessPw());
			pstmt.setString(3, bdto.getBusinessNum());
			pstmt.setString(4, bdto.getBusinessTitle());
			pstmt.setString(5, bdto.getBusinessAddr());
			pstmt.setString(6, bdto.getBusinessPhone());
			pstmt.setString(7, bdto.getBusinessHomepage());
			pstmt.executeUpdate();
			JdbcTemplate.commit(con);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			MessageEntity mEntity = new MessageEntity("error", 1);
			throw new CommonException(mEntity);
		}finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	
	/**
	 * 사업자 회원 정보 조회
	 * @param con
	 * @param bdto
	 * @throws CommonException
	 */
	public void businessInfo(Connection con, BusinessMemberDto bdto) throws CommonException{
		String sql = "select * from business where business_Id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getMemberId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bdto.setMemberId(rs.getString("business_Id"));
				bdto.setBusinessPw(rs.getString("business_Pw"));
				bdto.setBusinessNum(rs.getString("business_Num"));
				bdto.setBusinessTitle(rs.getString("business_Title"));
				bdto.setBusinessAddr(rs.getString("business_Addr"));
				bdto.setBusinessPhone(rs.getString("business_Phone"));
				bdto.setBusinessHomepage(rs.getString("business_Homepage"));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 사업자 정보 수정
	 * @param con
	 * @param bdto
	 * @throws CommonException
	 */
	public void updateBusinessInfo(Connection con, BusinessMemberDto bdto) throws CommonException{
		String sql = "update BUSINESS set business_Pw = ?, business_Title = ?, business_Addr = ?, business_Phone = ?, business_Homepage = ? where business_Id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getBusinessPw());
			pstmt.setString(2, bdto.getBusinessTitle());
			pstmt.setString(3, bdto.getBusinessAddr());
			pstmt.setString(4, bdto.getBusinessPhone());
			pstmt.setString(5, bdto.getBusinessHomepage());
			pstmt.setString(6, bdto.getMemberId());
			int rows=pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			MessageEntity message = new MessageEntity("error", 7);
			throw new CommonException(message);
		}finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	
	/**
	 * 사업자 회원 전체조회
	 * @param con
	 * @param list
	 * @throws CommonException
	 */
	public void businessList(Connection con, ArrayList<BusinessMemberDto> list) throws CommonException {
		String sql = "select * from BUSINESS";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			BusinessMemberDto bdto = null;
			while(rs.next()) {
				bdto = new BusinessMemberDto();
				bdto.setMemberId(rs.getString("business_Id"));
				bdto.setBusinessPw(rs.getString("business_Pw"));
				bdto.setBusinessNum(rs.getString("business_Num"));
				bdto.setBusinessTitle(rs.getString("business_Title"));
				bdto.setBusinessAddr(rs.getString("business_Addr"));
				bdto.setBusinessPhone(rs.getString("business_Phone"));
				bdto.setBusinessHomepage(rs.getString("business_Homepage"));
				list.add(bdto);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			MessageEntity messageEntity = new MessageEntity("error", 12);
			throw new CommonException(messageEntity);
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 사업자회원 아이디 찾기
	 * @param businessNum
	 * @param businessPhone
	 * @return
	 */
	public String findIdBusiness(String businessNum, String businessPhone) {
		String sql = "select business_Id from BUSINESS where business_Num = ? and business_Phone = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, businessNum);
			pstmt.setString(2, businessPhone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("business_Id");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(con);
		}
		return null;
	}

	
	/**
	 * 사업자회원 비밀번호 찾기
	 * @param businessId
	 * @param businessPhone
	 * @return
	 */
	public boolean findPwBusiness(String businessId, String businessPhone) {
		String sql = "select business_Pw from BUSINESS where business_Id = ? and business_Phone = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, businessId);
			pstmt.setString(2, businessPhone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(con);
		}
		return false;
	}
	
	
	/**
	 * 임시발급 비밀번호
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public int updatebusinessPw(String businessId, String businessPw) {
		String sql = "update BUSINESS set business_Pw = ? where business_Id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, businessPw);
			pstmt.setString(2, businessId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.commit(con);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(con);
		}
		return 0;
	}
	
	
	/**
	 * 사업자 회원탈퇴
	 * @param businessId
	 * @return
	 */
	public int deleteBusiness(String businessId) {
		String sql = "delete from business where business_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, businessId);
			int rows = pstmt.executeUpdate();
			JdbcTemplate.commit(con);
			if(rows == 1) {
				return rows;
			}
		} catch(SQLException e) {
			JdbcTemplate.rollback(con);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(con);
		}
		return 0;
	}
	
	
}
