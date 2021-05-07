/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.MainCategoryDto;

/**
 * @author 아연
 *
 */
public class MainCategoryDao {
    private static MainCategoryDao instance = new MainCategoryDao(); 
	
	public MainCategoryDao() {
	}
	
	public static MainCategoryDao getInstance() { 
		return instance;
	}


	/**
	 * 포인트 상품 메인카테고리목록 가져오기
	 * @param con
	 * @param categorylist
	 */
	public void categoryList(Connection con, ArrayList<MainCategoryDto> categorylist) {
		String sql = "SELECT * FROM main_category WHERE REGEXP_REPLACE(mcategory_num, '[^0-9]+') >= 5";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			MainCategoryDto mainCategoryDto = null;

			while (rs.next()) {
				mainCategoryDto = new MainCategoryDto();
				mainCategoryDto.setMcategoryNum(rs.getString("mcategory_num"));
				mainCategoryDto.setMcategoryName(rs.getString("mcategory_name"));

				categorylist.add(mainCategoryDto);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
}
