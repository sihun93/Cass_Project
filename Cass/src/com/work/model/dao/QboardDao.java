/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<QboardDto> getQboardList() {
		ArrayList<QboardDto> list = new ArrayList<QboardDto>();
		String sql = "select * from Q_board";
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
				//dto.setQboardImg(rs.getString("qboard_img"));
				dto.setMcategoryNum(rs.getString("mcategory_num"));
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
}
