package com.work.model.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.CommonException;
import com.work.model.dto.MemberDto;
import com.work.model.dto.MessageEntity;

public class MemberDao {
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	
	/**
	 * 회원 로그인 메서드
	 * @param con
	 * @param dto
	 * @throws CommonException
	 */
	public void memberLogin(Connection con, MemberDto dto) throws CommonException {
		String sql = "select * from MEMBER where member_Id = ? and member_Pw = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setMemberId(rs.getString("member_Id"));
				dto.setMemberPw(rs.getString("member_Pw"));
				dto.setMemberAddr(rs.getString("member_Addr"));
				dto.setMemberEmail(rs.getString("member_Email"));
				dto.setMemberMobile(rs.getString("member_Mobile"));
				dto.setMemberBirth(rs.getString("member_Birth"));
				dto.setGrade(rs.getString("grade"));
				dto.setPoint(rs.getInt("point"));
				dto.setSex(rs.getString("sex"));
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
	 * 회원가입 메서드
	 * @param con
	 * @param dto
	 * @throws CommonException
	 */
	public void addMember(Connection con, MemberDto dto) throws CommonException{
		String sql = "insert into MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getMemberPw());
			pstmt.setString(3, dto.getMemberAddr());
			pstmt.setString(4, dto.getMemberEmail());
			pstmt.setString(5, dto.getMemberMobile());
			pstmt.setString(6, dto.getMemberBirth());
			pstmt.setInt(7, dto.getPoint());
			pstmt.setString(8, dto.getGrade());
			pstmt.setString(9, dto.getSex());
			pstmt.executeUpdate();
			JdbcTemplate.commit(con);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			MessageEntity messageEntity = new MessageEntity("error", 0);
			throw new CommonException(messageEntity);
		} finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	
	/**
	 * 내정보조회 메서드
	 */
	
	public void myInfo(Connection con, MemberDto dto) throws CommonException{
		String sql = "select * from member where member_Id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setMemberId(rs.getString("member_Id"));
				dto.setMemberPw(rs.getString("member_Pw"));
				dto.setMemberAddr(rs.getString("member_Addr"));
				dto.setMemberEmail(rs.getString("member_Email"));
				dto.setMemberMobile(rs.getString("member_Mobile"));
				dto.setMemberBirth(rs.getString("member_Birth"));
				dto.setGrade(rs.getString("grade"));
				dto.setPoint(rs.getInt("point"));
				dto.setSex(rs.getString("sex"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
	}
	
	/**
	 * 내 정보 수정
	 * @param con
	 * @param dto
	 * @throws CommonException
	 */
	public void updateMyInfo(Connection con, MemberDto dto) throws CommonException{
		String sql = "update MEMBER set member_Pw = ?, member_Addr = ?, member_Email = ?, member_Mobile = ?, point = ? where member_Id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberPw());
			pstmt.setString(2, dto.getMemberAddr());
			pstmt.setString(3, dto.getMemberEmail());
			pstmt.setString(4, dto.getMemberMobile());
			pstmt.setInt(5, dto.getPoint());
			pstmt.setString(6, dto.getMemberId());
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			MessageEntity message = new MessageEntity("error", 6);
			throw new CommonException(message);
		}finally {
			JdbcTemplate.close(pstmt);
		}
	}
	
	
	/**
	 * 회원 전체조회 메서드
	 * @param con
	 * @param list
	 * @throws CommonException
	 */
	public void memberList(Connection con, ArrayList<MemberDto> list) throws CommonException {
		String sql = "select * from MEMBER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			MemberDto dto = null;
			while(rs.next()) {
				dto = new MemberDto();
				dto.setMemberId(rs.getString("member_Id"));
				dto.setMemberPw(rs.getString("member_Pw"));
				dto.setMemberAddr(rs.getString("member_Addr"));
				dto.setMemberEmail(rs.getString("member_Email"));
				dto.setMemberMobile(rs.getString("member_Mobile"));
				dto.setMemberBirth(rs.getString("member_Birth"));
				dto.setPoint(rs.getInt("point"));
				dto.setGrade(rs.getString("grade"));
				dto.setSex(rs.getString("sex"));
				list.add(dto);
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
	 * 아이디 찾기 메서드
	 * @param memberBirth
	 * @param memberMobile
	 * @return
	 */
	public String findIdMember(String memberBirth, String memberMobile) {
		String sql = "select member_Id from MEMBER where member_Birth = ? and member_Mobile = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBirth);
			pstmt.setString(2, memberMobile);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("member_Id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(con);
		}
		return null;
	}
	
	
	/**
	 * 비밀번호 찾기 메서드
	 * @param memberId
	 * @param memberMobile
	 * @return
	 */
	public boolean findPwMember(String memberId, String memberMobile) {
		String sql = "select member_Pw from MEMBER where member_Id = ? and member_Mobile = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberMobile);
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
	 * 비밀번호 찾기시 임시비밀번호 발급 메서드
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public int updateMemberPw(String memberId, String memberPw) {
		String sql = "update MEMBER set member_Pw=? where member_Id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcTemplate.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberId);
			
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
	 * 회원탈퇴
	 * +Q&A게시글 전체삭제가 함께 이루어져야함
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId) {
		String sql = "delete from member where member_Id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			int rows = pstmt.executeUpdate();
			JdbcTemplate.commit(conn);
			if(rows == 1) {
				return rows;
			}
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}
	
	
	/**
	 * 구매후 포인트 변경 (아연)
	 * @param con
	 * @param memberId
	 * @param memberPoint
	 */
	public void updatepoint(Connection con, String memberId, int memberPoint) {
		String sql = "update MEMBER set point = ? where member_Id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberPoint);
			pstmt.setString(2, memberId);
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}finally {
			JdbcTemplate.close(pstmt);
		}	
	}

	
	/**
	 * 아이디 입력해서 포인트값 가져오기
	 * @param memberId
	 * @return
	 */
	public MemberDto selectPoint(String memberId) {
		String sql = "select * from member where member_Id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMemberId(rs.getString("member_Id"));
				dto.setPoint(rs.getInt("point"));
				return dto;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	
	/**
	 * 포인트 변경
	 * @param dto
	 */
	public void pointModify(MemberDto dto) throws Exception {
		String sql = "update MEMBER set point = ? where member_Id = ?";
		
		PreparedStatement pstmt = null;
		Connection conn=null;
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getPoint());
			pstmt.setString(2, dto.getMemberId());
			
			int rows = pstmt.executeUpdate();
			if(rows == 0) {
				throw new Exception();
			}
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		}finally {
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(conn);
		}	
	}

	public void addData(Connection con, MemberDto dto) throws Exception  {
		String sql = "insert into DATACENTER  VALUES(?,0, sysdate, 0, sysdate, 0, sysdate, 0, sysdate, 'n')";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			pstmt.executeUpdate();
			JdbcTemplate.commit(con);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(pstmt);
		}
		
	}

	public void timeT() {
		String sql = "UPDATE DATACENTER set CHECK_MONTH = 't'";
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
			if (row != 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		
	}

	public void timeN() {
		String sql = "UPDATE DATACENTER set CHECK_MONTH = 'n',category1_count =0,category2_count = 0,"
				+ "category3_count =0,category4_count =0 where CHECK_MONTH = 't'";
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
			if (row != 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}
}
