package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;

public class MainBoardDao {
	private static MainBoardDao instance = new MainBoardDao();

	private MainBoardDao() {
	}

	public static MainBoardDao getInstance() {
		return instance;
	}

	/**
	 * 게시글 작성 미사용
	 * 
	 * @param dto
	 */
	public void boardInput(Connection conn, MainBoardDto dto) {
		String sql = "insert into mainboard values('mb'||seq_mainboard.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getBusinessId());
			stmt.setString(2, dto.getMcategoryNum());
			stmt.setString(3, dto.getScategoryNum());
			stmt.setString(4, dto.getMboardTitle());
			stmt.setInt(5, dto.getMboardScore());
			stmt.setString(6, dto.getMboardImg());
			stmt.setString(7, dto.getMboardInfo());
			stmt.setString(8, dto.getMboardContent());
			row = stmt.executeUpdate();
			if (row != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 작성 수정중
	 * 
	 * @param dto
	 * @throws SQLException
	 */
	public void boardInput(MainBoardDto dto) throws Exception {
		String sql = "insert into mainboard values('mb'||seq_mainboard.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		Connection conn = null;
		int row = 0;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getBusinessId());
			stmt.setString(2, dto.getMcategoryNum());
			stmt.setString(3, dto.getScategoryNum());
			stmt.setString(4, dto.getMboardTitle());
			stmt.setInt(5, dto.getMboardScore());
			stmt.setString(6, dto.getMboardImg());
			stmt.setString(7, dto.getMboardInfo());
			stmt.setString(8, dto.getMboardContent());
			row = stmt.executeUpdate();
			if (row != 1) {
				throw new Exception();
			}
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
	}

	/** 카테고리 목록 획득 */
	public void getMainCategoryList(Connection conn, ArrayList<MainCategoryDto> mainCategorylist) throws Exception {
		String sql = "SELECT * FROM main_category WHERE REGEXP_REPLACE(mcategory_num, '[^0-9]+') <= 4";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainCategoryDto dto = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dto = new MainCategoryDto();
				dto.setMcategoryNum(rs.getString("MCATEGORY_NUM"));
				dto.setMcategoryName(rs.getString("MCATEGORY_NAME"));
				mainCategorylist.add(dto);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/** 서브 카테고리 목록 획득 */
	public void getSubCategoryList(Connection conn, ArrayList<SubCategoryDto> subCategorylist) throws Exception {
		String sql = "select * from sub_category";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SubCategoryDto dto = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dto = new SubCategoryDto();
				dto.setScategoryNum(rs.getString("SCATEGORY_NUM"));
				dto.setMcategoryNum(rs.getString("MCATEGORY_NUM"));
				dto.setScategoryName(rs.getString("SCATEGORY_NAME"));
				subCategorylist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 목록 가저오기
	 * 
	 * @param list
	 * @param lastnum
	 * @param firstnum
	 * @throws SQLException
	 */
	public void getBoardList(Connection conn, ArrayList<MainBoardDto> list, int firstnum, int lastnum)
			throws SQLException {
		String sql = "SELECT * FROM (SELECT * FROM mainboard order by mboard_score, REGEXP_REPLACE(mboard_num,'[^0-9]+') ) where ROWNUM BETWEEN ? and ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, firstnum);
			stmt.setInt(2, lastnum);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dto = new MainBoardDto();
				dto.setMboardNum(rs.getString("mboard_Num"));
				dto.setBusinessId(rs.getString("business_Id"));
				dto.setMcategoryNum(rs.getString("mcategory_Num"));
				dto.setScategoryNum(rs.getString("scategory_Num"));
				dto.setMboardTitle(rs.getString("mboard_Title"));
				dto.setMboardImg(rs.getString("mboard_Img"));
				dto.setMboardInfo(rs.getString("mboard_Info"));
				dto.setMboardScore(rs.getInt("mboard_Score"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	public void getBoardListForMc(Connection conn, ArrayList<MainBoardDto> list, int firstnum, int lastnum,
			String mcategoryNum) throws SQLException {
		String sql = "SELECT * FROM (SELECT * FROM mainboard where mcategory_Num = ?  order by mboard_score, REGEXP_REPLACE(mboard_num,'[^0-9]+') ) where ROWNUM BETWEEN ? and ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mcategoryNum);
			stmt.setInt(2, firstnum);
			stmt.setInt(3, lastnum);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dto = new MainBoardDto();
				dto.setMboardNum(rs.getString("mboard_Num"));
				dto.setBusinessId(rs.getString("business_Id"));
				dto.setMcategoryNum(rs.getString("mcategory_Num"));
				dto.setScategoryNum(rs.getString("scategory_Num"));
				dto.setMboardTitle(rs.getString("mboard_Title"));
				dto.setMboardImg(rs.getString("mboard_Img"));
				dto.setMboardInfo(rs.getString("mboard_Info"));
				dto.setMboardScore(rs.getInt("mboard_Score"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void getBoardListForSc(Connection conn, ArrayList<MainBoardDto> list, int firstnum, int lastnum,
			String scategoryNum) throws SQLException {
		String sql = "SELECT * FROM (SELECT * FROM mainboard where scategory_Num = ?  order by mboard_score, REGEXP_REPLACE(mboard_num,'[^0-9]+') ) where ROWNUM BETWEEN ? and ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, scategoryNum);
			stmt.setInt(2, firstnum);
			stmt.setInt(3, lastnum);
			rs = stmt.executeQuery();
			while (rs.next()) {
				dto = new MainBoardDto();
				dto.setMboardNum(rs.getString("mboard_Num"));
				dto.setBusinessId(rs.getString("business_Id"));
				dto.setMcategoryNum(rs.getString("mcategory_Num"));
				dto.setScategoryNum(rs.getString("scategory_Num"));
				dto.setMboardTitle(rs.getString("mboard_Title"));
				dto.setMboardImg(rs.getString("mboard_Img"));
				dto.setMboardInfo(rs.getString("mboard_Info"));
				dto.setMboardScore(rs.getInt("mboard_Score"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 수 반환
	 * 
	 * @throws SQLException
	 */
	public void boardcount(Connection conn, int counter) throws SQLException {
		String sql = "select COUNT(*) as C from mainboard";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				counter = rs.getInt("C");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/** 게시글 삭제 */
	public void boardDelete(Connection conn) {
		String sql = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 상세
	 * 
	 * @param dto
	 * @throws SQLException
	 */
	public void boardDetail(Connection conn, MainBoardDto dto) throws SQLException {
		String sql = "select * from mainboard where mboard_num = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMboardNum());
			rs = stmt.executeQuery();
			if (rs.next()) {
				dto.setBusinessId(rs.getString("business_Id"));
				dto.setMcategoryNum(rs.getString("mcategory_Num"));
				dto.setScategoryNum(rs.getString("scategory_Num"));
				dto.setMboardTitle(rs.getString("mboard_Title"));
				dto.setMboardScore(rs.getInt("mboard_Score"));
				dto.setMboardImg(rs.getString("mboard_Img"));
				dto.setMboardInfo(rs.getString("mboard_Info"));
				dto.setMboardContent(rs.getString("mboard_Content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 게시글 수정
	 * 
	 * @throws Exception
	 */
	public void boardUpdate(Connection conn, MainBoardDto dto) throws Exception {
		String sql = "UPDATE mainboard SET mcategory_Num = ?, scategory_Num = ?, "
				+ "mboard_Title = ?,mboard_Img = ?,mboard_Info = ?,mboard_Content = ? " + "where mboard_Num = ?";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMcategoryNum());
			stmt.setString(2, dto.getScategoryNum());
			stmt.setString(3, dto.getMboardTitle());
			stmt.setString(4, dto.getMboardImg());
			stmt.setString(5, dto.getMboardInfo());
			stmt.setString(6, dto.getMboardContent());
			stmt.setString(7, dto.getMboardNum());
			row = stmt.executeUpdate();
			if (row != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
		}
	}

	/** 게시글 검색 */
	public void boardFind(Connection conn) {
		String sql = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 리뷰 검색
	 * 
	 * @param lastnum
	 * @param firstnum
	 * @throws SQLException
	 */
	public void getReviewList(Connection conn, ArrayList<ReviewDto> list, String mboardNum, int firstnum, int lastnum)
			throws SQLException {
		String sql = "select * from review where mboard_num = ? and ROWNUM BETWEEN ? and ? order by review_Date desc";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ReviewDto dto = null;
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mboardNum);
			stmt.setInt(2, firstnum);
			stmt.setInt(3, lastnum);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dto = new ReviewDto();
				dto.setReviewNum(rs.getInt("review_Num"));
				dto.setMboardNum(rs.getString("mboard_Num"));
				dto.setMemberId(rs.getString("member_Id"));
				dto.setScore(rs.getInt("review_score"));
				dto.setReviewContent(rs.getString("review_Content"));
				dto.setReviewImg(rs.getString("review_Img"));
				dto.setReviewDate(rs.getString("review_Date"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	public void reviewcount(Connection conn, int reviewcounter, String mboardNum) throws SQLException {
		String sql = "select COUNT(*) as C from review where mboard_num = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mboardNum);
			rs = stmt.executeQuery();
			if (rs.next()) {
				reviewcounter = rs.getInt("C");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}

	}

	public void inputReview(Connection conn, ReviewDto dto) throws Exception {
		String sql = "insert into review values('seq_review.nextval,?,?,?,?,?,sysdate)";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMboardNum());
			stmt.setString(2, dto.getMemberId());
			stmt.setInt(3, dto.getScore());
			stmt.setString(4, dto.getReviewContent());
			stmt.setString(5, dto.getReviewImg());
			row = stmt.executeUpdate();
			if (row != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
		}
	}


}
