/**
 * 
 */
package com.work.model.biz;

import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.PointBuyDao;
import com.work.model.dao.PointDao;
import com.work.model.dto.PointBuyDto;


/**
 * @author 최아연
 * 포인트 상품 구매내역 Biz
 */
public class PointBuyBiz {
	private PointBuyDao dao = PointBuyDao.getInstance();

	/**
	 * 구매내역 저장
	 * @param pointBuyDto
	 * @throws Exception 
	 */
	public void buyinput(PointBuyDto pointBuyDto) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.buyinput(con, pointBuyDto);
			JdbcTemplate.commit(con);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.close(con);
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}

	/**
	 * 구매내역 조회
	 * @param memberId
	 * @param pointBuylist 
	 */
	public void buylist(String memberId, ArrayList<PointBuyDto> pointBuylist) {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.buylist(con, memberId, pointBuylist);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}
}
