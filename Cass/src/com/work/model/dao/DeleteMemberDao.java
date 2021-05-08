/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MasterMemberDto;
import com.work.model.dto.MemberDto;

/**
 * @author 성재
 *
 */
public class DeleteMemberDao {
    private static DeleteMemberDao instance = new DeleteMemberDao(); 
	
	public DeleteMemberDao() {
	}
	
	public static DeleteMemberDao getInstance() { 
		return instance;
	}
	/** 리뷰 삭제 */
	public void deletereview(Connection conn, MemberDto dto) throws Exception {
		String sql = "DELETE review WHERE Member_Id =?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	
	/** Q&A 삭제 */
	public void deleteQboard(Connection conn, MasterMemberDto dto) throws Exception {
		String sql = "delete from q_board where Member_Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(stmt);
		}
	}
	
	/** 작성한 게시글의 번호 목록 반환 */
	public void findMainBoardList(Connection conn, BusinessMemberDto dto,ArrayList<String> boardNumList) throws Exception {
		String sql = "select MBOARD_NUM from mainboard where BUSINESS_ID=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			rs = stmt.executeQuery();
			while (rs.next()){
				boardNumList.add(rs.getString("MBOARD_NUM"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	/** 해당 게시글 리뷰 전체 삭제  */
	public void deletereview(Connection conn, String boardNum) throws Exception {
		String sql = "DELETE review WHERE mboard_Num =?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,boardNum);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	/** 작성한 게시글 삭제 */
	public void deleteMainBoard(Connection conn, BusinessMemberDto dto) throws Exception {
		String sql = "DELETE mainboard where BUSINESS_ID=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(stmt);
		}
	}
}
