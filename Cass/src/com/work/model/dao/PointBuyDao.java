/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.PointBuyDto;

/**
 * @author 최아연
 * 포인트 상품 구매내역 Dao
 */
public class PointBuyDao {
private static PointBuyDao instance = new PointBuyDao(); 
	
	public PointBuyDao() {
	}
	
	public static PointBuyDao getInstance() { 
		return instance;
	}

	/**
	 * 구매내역 저장
	 * @param con
	 * @param pointBuyDto
	 * @throws Exception 
	 */
	public void buyinput(Connection con, PointBuyDto pointBuyDto) throws Exception {
		String sql = "insert into point_buy values('b' || buy_num_seq.nextval,?,?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pointBuyDto.getPboard_num());
			stmt.setString(2, pointBuyDto.getMember_id());
			stmt.setString(3, pointBuyDto.getPboard_title());
			stmt.setString(4, pointBuyDto.getPboard_img());
			stmt.setInt(5, pointBuyDto.getPboardCount());
			stmt.setInt(6, pointBuyDto.getPboardPrice());
			stmt.setString(7, pointBuyDto.getBuyDate());
			
			int row = stmt.executeUpdate();
			if (row == 0) {
				
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcTemplate.close(stmt);		
		}		
		
	}

	/**
	 * 구매내역 조회
	 * @param con
	 * @param memberId
	 * @param pointBuylist 
	 */
	public void buylist(Connection con, String memberId, ArrayList<PointBuyDto> pointBuylist) {
		String sql = "select p.buy_num, p.pboard_num, p.pboard_title, p.pboard_img, p.pboard_count, p.pboard_price, p.buy_date from member m join point_buy p using(member_id) where member_id=? order by p.buy_num desc";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			
			PointBuyDto pointBuyDto = null;
			while (rs.next()) {
				pointBuyDto = new PointBuyDto();
				pointBuyDto.setBuyNum(rs.getString("buy_num"));
				pointBuyDto.setPboard_num(rs.getString("pboard_num"));
				pointBuyDto.setPboard_title(rs.getString("pboard_title"));
				pointBuyDto.setPboard_img(rs.getString("pboard_img"));
				pointBuyDto.setPboardCount(rs.getInt("pboard_count"));
				pointBuyDto.setPboardPrice(rs.getInt("pboard_price"));
				pointBuyDto.setBuyDate(rs.getString("buy_date"));
				
				pointBuylist.add(pointBuyDto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
 
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(rs);
		}		
		
	}
}
