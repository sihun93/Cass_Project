/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.AboardDto;
import com.work.model.dto.MasterMemberDto;
import com.work.model.dto.QboardDto;;

/**
 * @author 박민주
 *
 */
public class QboardDao {
	private static QboardDao instance = new QboardDao();
	private QboardDao(){}
	public static QboardDao getInstance() {
		return instance;
	}
	
	/** Q&A 게시판 게시글 전체 조회
	 * @param 
	 * @return ArrayList<QboardDto> 없으면 null
	 */
	public ArrayList<QboardDto> getQboardList() {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from q_board ORDER BY to_number(REGEXP_REPLACE(qboard_num, '[^0-9]+')) DESC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				QboardDto dto = new QboardDto();
				dto.setQboardNum(rs.getString("qboard_num"));
				dto.setQboardTitle(rs.getString("qboard_title"));
				dto.setQboardContent(rs.getString("qboard_content"));
				dto.setQboardDate(rs.getString("Qboard_date"));
				dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMemberId(rs.getString("member_id"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 게시글 상세 조회
	 * @param qboardNum Q게시판 번호
	 * @return ArrayList<QboardDto> 없으면 null
	 */
	public ArrayList<QboardDto> getQboardDetail(String qboardNum) {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from Q_board where qboard_num=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardNum);
			rs = stmt.executeQuery();
			while(rs.next()) {
				QboardDto dto = new QboardDto();
				dto.setQboardNum(rs.getString("qboard_num"));
				dto.setQboardTitle(rs.getString("qboard_title"));
				dto.setQboardContent(rs.getString("qboard_content"));
				dto.setQboardDate(rs.getString("Qboard_date"));
				dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMemberId(rs.getString("member_id"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 답글 조회
	 * @param qboardNum 게시글 번호
	 * @return ArrayList<AboardDto> 없으면 null
	 */
	public ArrayList<AboardDto> getAboardList(String qboardNum){
		ArrayList<AboardDto> list = new ArrayList<AboardDto>();
		String sql = "select * from A_board where qboard_num=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardNum);
			rs = stmt.executeQuery();
			while(rs.next()) {
				AboardDto dto = new AboardDto();
				dto.setQboardNum(rs.getString("Qboard_num"));
				dto.setAboardWriter(rs.getString("Aboard_writer"));
				dto.setAboardDate(rs.getString("Aboard_date"));
				dto.setAboardContent(rs.getString("Aboard_content"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 답글 등록
	 * @param qboardNum 게시글 번호, aboardContent 답글 내용
	 * @param dto 
	 * @return ArrayList<AboardDto> 없으면 null
	 */
	public ArrayList<AboardDto> addAboard(String qboardNum, String aboardContent, MasterMemberDto dto) {
		ArrayList<AboardDto> list = new ArrayList<AboardDto>();
		String sql = "insert into A_board values(?,?, ?,sysdate)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardNum);
			stmt.setString(2, dto.getMemberId());
			stmt.setString(3, aboardContent);
			rs = stmt.executeQuery();
			JdbcTemplate.commit(conn);
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 게시글 등록
	 * @param qboardTitle 게시글 제목 , qboardContent 게시글 내용, qboardImg 이미지, memberId 아이디
	 * @return 등록 성공이면 1, 실패면 0 반환
	 */
	public int addQboard(String qboardTitle, String qboardContent, String qboardImg, String memberId) {
		String sql = "insert into q_board(Qboard_num, Qboard_title, Qboard_content, Qboard_img, member_id) values('qb'||q_num_seq.nextval, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardTitle);
			stmt.setString(2, qboardContent);
			stmt.setString(3, qboardImg);
			stmt.setString(4, memberId);
			rs = stmt.executeQuery();
			JdbcTemplate.commit(conn);
			return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}
	
	/** Q&A 게시글 삭제
	 * @param qboardNum 게시글 번호
	 * @return 삭제 성공이면 1, 실패면 0 반환
	 */
	public int deleteQboard(String qboardNum) {
		String sql = "delete from q_board where qboard_num=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardNum);
			rs = stmt.executeQuery();
			JdbcTemplate.commit(conn);
			return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}
	
	/** Q&A 게시글 검색(내용+제목)
	 * @param txtKeyWord 조회할 키워드
	 * @return ArrayList<QboardDto> 없으면 null
	 */
	public ArrayList<QboardDto> getQboardByA(String txtKeyWord) {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from Q_board where qboard_title like ? or qboard_content like ? ORDER BY to_number(REGEXP_REPLACE(qboard_num, '[^0-9]+')) DESC";
		String Keyword = "%" + txtKeyWord + "%";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, Keyword);
			stmt.setString(2, Keyword);
			rs = stmt.executeQuery();
			while(rs.next()) {
				QboardDto dto = new QboardDto();
				dto.setQboardNum(rs.getString("qboard_num"));
				dto.setQboardTitle(rs.getString("qboard_title"));
				dto.setQboardContent(rs.getString("qboard_content"));
				dto.setQboardDate(rs.getString("Qboard_date"));
				dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMemberId(rs.getString("member_id"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 게시글 검색(제목)
	 * @param txtKeyWord 조회할 키워드
	 * @return ArrayList<QboardDto> 없으면 null
	 */
	public ArrayList<QboardDto> getQboardByT(String txtKeyWord) {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from Q_board where qboard_title like ? ORDER BY to_number(REGEXP_REPLACE(qboard_num, '[^0-9]+')) DESC";
		String Keyword = "%" + txtKeyWord + "%";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, Keyword);
			rs = stmt.executeQuery();
			while(rs.next()) {
				QboardDto dto = new QboardDto();
				dto.setQboardNum(rs.getString("qboard_num"));
				dto.setQboardTitle(rs.getString("qboard_title"));
				dto.setQboardContent(rs.getString("qboard_content"));
				dto.setQboardDate(rs.getString("Qboard_date"));
				dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMemberId(rs.getString("member_id"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 게시글 검색(제목)
	 * @param txtKeyWord 조회할 키워드
	 * @return ArrayList<QboardDto> 없으면 null
	 */
	public ArrayList<QboardDto> getQboardByC(String txtKeyWord) {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from Q_board where qboard_content like ? ORDER BY to_number(REGEXP_REPLACE(qboard_num, '[^0-9]+')) DESC";
		String Keyword = "%" + txtKeyWord + "%";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, Keyword);
			rs = stmt.executeQuery();
			while(rs.next()) {
				QboardDto dto = new QboardDto();
				dto.setQboardNum(rs.getString("qboard_num"));
				dto.setQboardTitle(rs.getString("qboard_title"));
				dto.setQboardContent(rs.getString("qboard_content"));
				dto.setQboardDate(rs.getString("Qboard_date"));
				dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMemberId(rs.getString("member_id"));
				list.add(dto);	
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return null;
	}
	
	/** Q&A 게시글 수정
	 * @param qboardNum 게시글 번호, qboardTitle 게시글 제목 , qboardContent 게시글 내용, qboardImg 이미지
	 * @return 수정 성공이면 1, 실패면 0 반환
	 */
	public int updateQboard(String qboardNum, String qboardTitle, String qboardContent, String qboardImg) {
		String sql = "update q_board set qboard_title=?, qboard_content=?, qboard_img=? where qboard_num=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qboardTitle);
			stmt.setString(2, qboardContent);
			stmt.setString(3, qboardImg);
			stmt.setString(4, qboardNum);
			rs = stmt.executeQuery();
			JdbcTemplate.commit(conn);
			return 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}
}

