/**
 * 
 */
package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.MainCategoryDao;
import com.work.model.dto.MainCategoryDto;

/**
 * @author 아연
 *
 */
public class MainCategoryBiz {
	MainCategoryDao dao = new MainCategoryDao();

	/**
	 * 포인트 상품 메인 카테고리목록 가져오기
	 * @param categorylist
	 */
	public void categoryList(ArrayList<MainCategoryDto> categorylist) {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.categoryList(con, categorylist);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}  
}
