/**
 * 
 */
package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.PointDao;
import com.work.model.dto.PointDto;

/**
 * @author 최아연
 * 마일리지 Biz
 */
public class PointBiz {

	private PointDao dao = PointDao.getInstance();

	/**
	 * 마일리지 상품 등록
	 * @param pointDto
	 * @param fileRealName 
	 * @param fileName 
	 * @throws Exception
	 */
	public void pointInput(PointDto pointDto) throws Exception {
		Connection con = JdbcTemplate.getConnection();

		try {
			dao.pointInput(con, pointDto);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(con);
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}

	/**
	 * 마일리지 상품 전체조회
	 * @param pointlist
	 * @throws Exception 
	 */
	public void pointList(ArrayList<PointDto> pointlist) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.pointList(con, pointlist);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}

	/**
	 * 마일리지 상세조회
	 * @param pointDto
	 * @param pboardNum
	 * @throws Exception 
	 */
	public void pointInfo(PointDto pointDto, String pboardNum) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.pointInfo(con, pointDto, pboardNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}
}
