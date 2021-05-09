/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.SubCategoryDto;

/**
 * @author 박민주
 *
 */
public class CategoryDao {
	
	private static CategoryDao instance = new CategoryDao();
	private CategoryDao(){}
	public static CategoryDao getInstance() {
		return instance;
	}
	/** 메인카테고리 목록 조회
	 * @param 
	 * @return ArrayList<MainCategoryDto> 없으면 null
	 */
	public ArrayList<MainCategoryDto> getMainCategory() {
		ArrayList<MainCategoryDto> list = new ArrayList<MainCategoryDto>();
		String sql = "select * from main_category where mcategory_num='mc1' or mcategory_num='mc2' or mcategory_num='mc3' or mcategory_num='mc4'";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				MainCategoryDto dto = new MainCategoryDto();
				dto.setMcategoryName(rs.getString("mcategory_name"));
				dto.setMcategoryNum(rs.getString("mcategory_num"));
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
	
	/** 서브카테고리 목록 조회
	 * @param 
	 * @return ArrayList<SubCategoryDto> 없으면 null
	 */
	public ArrayList<SubCategoryDto> getSubCategory() {
		ArrayList<SubCategoryDto> list = new ArrayList<SubCategoryDto>();
		String sql = "select * from sub_category";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SubCategoryDto dto = new SubCategoryDto();
				dto.setMcategoryNum(rs.getString("mcategory_num"));
				dto.setScategoryName(rs.getString("scategory_name"));
				dto.setScategoryNum(rs.getString("scategory_num"));
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
	
	/** 평균 평점이 높은 게시글 조회
	 * @param 
	 * @return ArrayList<MainBoardDto> 없으면 null
	 */
	public ArrayList<MainBoardDto> getBestMain() {
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		String sql = "SELECT * " +  
				"FROM(SELECT * " + 
				"FROM mainboard m " + 
				"JOIN (SELECT trunc(AVG(r.review_score)) as score ,COUNT(*) as count, mboard_num FROM  mainboard m JOIN review  r USING(mboard_num) " + 
				"GROUP BY mboard_num) s " + 
				"USING(mboard_num) " + 
				"order by score desc, to_number(REGEXP_REPLACE(mboard_num,'[^0-9]+'))) " + 
				"WHERE ROWNUM <=2";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				MainBoardDto dto = new MainBoardDto();
				dto.setMboardNum(rs.getString("mboard_Num"));
				dto.setBusinessId(rs.getString("business_Id"));
				dto.setMcategoryNum(rs.getString("mcategory_Num"));
				dto.setScategoryNum(rs.getString("scategory_Num"));
				dto.setMboardTitle(rs.getString("mboard_Title"));
				dto.setMboardImg(rs.getString("mboard_Img"));
				dto.setMboardInfo(rs.getString("mboard_Info"));
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


}
