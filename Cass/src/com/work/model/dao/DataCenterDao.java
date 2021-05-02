/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.DataCenterDto;


/**
 * @author 백시훈
 *
 */
public class DataCenterDao {
	private static DataCenterDao instance = new DataCenterDao();
	private DataCenterDao(){}
	public static DataCenterDao getInstance() {
		return instance;
	}
	public int getSexMSelecter() {
		String sql = "select count(sex) from member where sex='M'";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("count(sex)");
			}
			return count;
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
	public int getCountTotal() {
		String sql = "select count(*) from member";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				count = rs.getInt("count(*)");
			}
			return count;
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
