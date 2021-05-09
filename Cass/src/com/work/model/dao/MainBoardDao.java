package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.MasterMemberDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;
import com.work.util.Utility;

public class MainBoardDao {
	private static MainBoardDao instance = new MainBoardDao();

	private MainBoardDao() {
	}

	public static MainBoardDao getInstance() {
		return instance;
	}
	
	/**
	 * 게시글 작성
	 * 
	 * @param dto
	 * @throws SQLException
	 */
	public void boardInput(Connection conn, MainBoardDto dto) throws Exception {
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
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
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
	 */
	public void getBoardList(Connection conn, HashMap<Integer, ArrayList<MainBoardDto>> boardAllList)
			throws SQLException {
		String sql = "SELECT * FROM mainboard m "
				+ "left outer JOIN (SELECT trunc(AVG(r.review_score)) as score ,COUNT(review_Num) as count, mboard_num FROM  mainboard m JOIN review  r USING(mboard_num) GROUP BY mboard_num) s "
				+ "USING(mboard_num) "
				+ "order by mboard_score, to_number(REGEXP_REPLACE(mboard_num,'[^0-9]+'))";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int count = 0;
			int key = 1;
			while (rs.next()) {
				if (count < 5) {
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				} else {
					boardAllList.put(key, list);
					key += 1;
					count = 0;
					list = new ArrayList<MainBoardDto>();
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				}
			}
			if (list.size() != 0) {
				boardAllList.put(key, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	/** 메인 카테고리 게시글 */
	public void getBoardListForMc(Connection conn, HashMap<Integer, ArrayList<MainBoardDto>> boardAllList,
			String mcategoryNum) throws SQLException {
		String sql = "SELECT * FROM mainboard m "
				+ "left outer JOIN (SELECT trunc(AVG(r.review_score)) as score ,COUNT(review_Num) as count, mboard_num FROM  mainboard m JOIN review  r USING(mboard_num) GROUP BY mboard_num) s "
				+ "USING(mboard_num) "
				+ "WHERE mcategory_Num = ? "
				+ "order by mboard_score, to_number(REGEXP_REPLACE(mboard_num,'[^0-9]+'))";

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mcategoryNum);
			rs = stmt.executeQuery();
			int count = 0;
			int key = 1;
			while (rs.next()) {
				if (count < 5) {
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				} else {
					boardAllList.put(key, list);
					key += 1;
					count = 0;
					list = new ArrayList<MainBoardDto>();
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				}
			}
			if (list.size() != 0) {
				boardAllList.put(key, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	/** 서브 카테고리 게시글 */
	public void getBoardListForSc(Connection conn, HashMap<Integer, ArrayList<MainBoardDto>> boardAllList,
			String scategoryNum) throws SQLException {
		String sql = "left outer SELECT * FROM mainboard m "
				+ "left outer JOIN (SELECT trunc(AVG(r.review_score)) as score ,COUNT(review_Num) as count, mboard_num FROM  mainboard m JOIN review  r USING(mboard_num) GROUP BY mboard_num) s "
				+ "USING(mboard_num) "
				+ "WHERE scategory_Num = ? "
				+ "order by mboard_score, to_number(REGEXP_REPLACE(mboard_num,'[^0-9]+'))";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MainBoardDto dto = null;
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, scategoryNum);
			rs = stmt.executeQuery();
			int count = 0;
			int key = 1;
			while (rs.next()) {
				if (count < 5) {
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				} else {
					boardAllList.put(key, list);
					key += 1;
					count = 0;
					list = new ArrayList<MainBoardDto>();
					dto = new MainBoardDto();
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setBusinessId(rs.getString("business_Id"));
					dto.setMcategoryNum(rs.getString("mcategory_Num"));
					dto.setScategoryNum(rs.getString("scategory_Num"));
					dto.setMboardTitle(rs.getString("mboard_Title"));
					dto.setMboardImg(rs.getString("mboard_Img"));
					dto.setMboardInfo(rs.getString("mboard_Info"));
					if(rs.getInt("count") >9) {
						dto.setMboardScore(rs.getInt("score"));
					}else {
						dto.setMboardScore(rs.getInt("mboard_Score"));
					}
					list.add(dto);
					count += 1;
				}
			}
			if (list.size() != 0) {
				boardAllList.put(key, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	
	
	/** 게시글 삭제  */
	public void boardDelete(Connection conn, MainBoardDto dto) throws Exception {
		String sql = "DELETE mainboard WHERE mboard_Num =?";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMboardNum());
			row = stmt.executeUpdate();
			if(row != 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(stmt);
		}
	}
	/** 해당 게시글 리뷰 전체 삭제  */
	public void deletereview(Connection conn, MainBoardDto dto) throws Exception {
		String sql = "DELETE review WHERE mboard_Num =?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMboardNum());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
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
		String sql = "SELECT * FROM mainboard m "
				+ "left outer JOIN (SELECT trunc(AVG(r.review_score)) as score ,COUNT(review_Num) as count, mboard_num FROM  mainboard m JOIN review  r USING(mboard_num) GROUP BY mboard_num) s "
				+ "USING(mboard_num) "
				+ "WHERE mboard_Num = ? "
				+ "order by mboard_score, to_number(REGEXP_REPLACE(mboard_num,'[^0-9]+'))";
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
				if(rs.getInt("count") >9) {
					dto.setMboardScore(rs.getInt("score"));
				}else {
					dto.setMboardScore(rs.getInt("mboard_Score"));
				}
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
	public void getReviewList(Connection conn, HashMap<Integer, ArrayList<ReviewDto>> reviewAllList, String mboardNum)
			throws SQLException {
		String sql = "select * from review where mboard_num = ? order by review_Num desc";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ReviewDto dto = null;
		ArrayList<ReviewDto> list = new ArrayList<ReviewDto>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mboardNum);
			rs = stmt.executeQuery();
			int count = 0;
			int key = 1;
			while(rs.next()) {
				if(count <10) {
					dto = new ReviewDto();
					dto.setReviewNum(rs.getInt("review_Num"));
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setMemberId(rs.getString("member_Id"));
					dto.setScore(rs.getInt("review_score"));
					dto.setReviewContent(rs.getString("review_Content"));
					dto.setReviewImg(rs.getString("review_Img"));
					dto.setReviewDate(rs.getString("review_Date"));
					list.add(dto);
					count +=1;
				}else {
					reviewAllList.put(key, list);
					key += 1;
					count = 0;
					list = new ArrayList<ReviewDto>();
					dto = new ReviewDto();
					dto.setReviewNum(rs.getInt("review_Num"));
					dto.setMboardNum(rs.getString("mboard_Num"));
					dto.setMemberId(rs.getString("member_Id"));
					dto.setScore(rs.getInt("review_score"));
					dto.setReviewContent(rs.getString("review_Content"));
					dto.setReviewImg(rs.getString("review_Img"));
					dto.setReviewDate(rs.getString("review_Date"));
					list.add(dto);
					count +=1;
				}
			}
			if(list.size() != 0) {
				reviewAllList.put(key, list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	/** 게시글 리뷰 수 */
	public int reviewcount(String mboardNum) {
		String sql = "select COUNT(*) from review where mboard_num = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int reviewcounter =0;
		try {
			conn= JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mboardNum);
			rs = stmt.executeQuery();
			if (rs.next()) {
				reviewcounter = rs.getInt(1);
				return reviewcounter;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return 0;
	}
	/** 리뷰 등록 */
	public void inputReview(Connection conn, ReviewDto dto) throws Exception {
		String sql = "insert into review values(seq_review.nextval,?,?,?,?,?,sysdate)";
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
	/** 리뷰 삭제 */
	public void deletereview(Connection conn, ReviewDto dto) throws Exception {
		String sql = "DELETE review WHERE mboard_Num =? and Member_Id =? and Review_Num = ?";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMboardNum());
			stmt.setString(2, dto.getMemberId());
			stmt.setInt(3, dto.getReviewNum());
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
	
	/** 리뷰 수정 */
	public void updatereview(Connection conn, ReviewDto dto) throws Exception {
		String sql = "UPDATE review set REVIEW_CONTENT = ? ,REVIEW_DATE = sysdate WHERE Review_Num = ?";
		PreparedStatement stmt = null;
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getReviewContent());
			stmt.setInt(2, dto.getReviewNum());
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

	public boolean checkreview(String memberId, String mboardNum) {
		String sql = "select * from review where mboard_num = ? member_Id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mboardNum);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return false;
	}

	public boolean getTime(MasterMemberDto dto, String columndate, String columncount) {
		String sql = "select to_char("+columndate
				+ ",'HH24-MI-SS') as T,"+columncount+" from DATACENTER where member_Id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt(columncount) == 0) {
					return true;
				}else {
					String checkTime = rs.getString("T");
					String currentTime = Utility.getCurrentDate("HH-mm-ss");
					String[] checkTimeArray = checkTime.split("-");
					String[] currentTimeArray = currentTime.split("-");
					int checkTimeValue = Integer.parseInt(checkTimeArray[0])*60+Integer.parseInt(checkTimeArray[1]);
					int currentTimeValue = Integer.parseInt(currentTimeArray[0])*60+Integer.parseInt(currentTimeArray[1]);
					if((currentTimeValue-checkTimeValue) >=30 || ((currentTimeValue-checkTimeValue) >=-1410 && (currentTimeValue-checkTimeValue) <0)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(conn);
		}
		return false;
	}

	public void setdata(MasterMemberDto dto, String columncount) {
		String sql = "UPDATE DATACENTER set "+columncount+" = to_number(+columncount)+1 WHERE member_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getMemberId());
			row = stmt.executeUpdate();
			if (row != 1) {
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
