/**
 * 
 */
package com.work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.DataDto;

import javafx.scene.chart.PieChart.Data;


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
	/**
	 * 남성 이용자 이원 구하는 메서드
	 * @return
	 */
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
	/**
	 * 전체이용자 회원수 구하는 메서드
	 * @return
	 */
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
	/**
	 * 나이대별 인원수를 구하는 메서드
	 * @return 10대, 20대, 30대, 40대, 50대로 순서대로 ArrayList로 반환
	 */
	public ArrayList<Integer> getAge(){
		String sql = "SELECT SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1) AS AGE"
				+",COUNT(SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)) AS COUNT"
				+"  FROM MEMBER"
				+" GROUP BY SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)"
				+" ORDER BY AGE";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
					list.add(rs.getInt("count"));
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
	};
	/**
	 * 인기 카테고리 반환 메서드
	 * @return 카테고리 조회수를 반환
	 */
	public ArrayList<Integer> getCategoryCount(){
		String sql = "select sum(category1_count) as category1, sum(category2_count) as category2,"
				+"sum(category3_count) as category3, sum(category4_count) as category4 from datacenter";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("category1"));
				list.add(rs.getInt("category2"));
				list.add(rs.getInt("category3"));
				list.add(rs.getInt("category4"));
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
	/**
	 * 성별(남)별 인기카테고리 조회수 반환 메서드
	 * @return 카테고리별 조회수 반환
	 */
	public ArrayList<Integer> getSelectMCount() {
		String sql = "select m.sex, sum(d.category1_count) as cate1, sum(d.category2_count) as cate2, "
				+"sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) where m.sex='M' group by m.sex";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	/**
	 * 성별(여)별 인기카테고리 조회수 반환 메서드
	 * @return 여성의 카테고리별 조회수 반환 메서드
	 */
	public ArrayList<Integer> getSelectFCount() {
		String sql = "select m.sex, sum(d.category1_count) as cate1, sum(d.category2_count) as cate2, "
				+"sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) where m.sex='F' group by m.sex";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	/**
	 * 10대 인기카테고리 반환 메서드
	 * @return 10대 카테고리 조회수 반환
	 */
	public ArrayList<Integer> getAge10Select() {
		String sql = "select sum(d.category1_count) as cate1, sum(d.category2_count) as cate2,"
				+" sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) "
				+"where SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)=1 "
				+"GROUP by TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	/**
	 * 20대 인기카테고리 반환 메서드
	 * @return 20대 카테고리 조회수 반환
	 */
	public ArrayList<Integer> getAge20Select() {
		String sql = "select sum(d.category1_count) as cate1, sum(d.category2_count) as cate2,"
				+" sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) "
				+"where SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)=2 "
				+"GROUP by TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	/**
	 * 30대 인기카테고리 반환 메서드
	 * @return 30대 카테고리 조회수 반환
	 */
	public ArrayList<Integer> getAge30Select() {
		String sql = "select sum(d.category1_count) as cate1, sum(d.category2_count) as cate2,"
				+" sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) "
				+"where SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)=3 "
				+"GROUP by TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	/**
	 * 40대 인기카테고리 반환 메서드
	 * @return 40대 카테고리 조회수 반환
	 */
	public ArrayList<Integer> getAge40Select() {
		String sql = "select sum(d.category1_count) as cate1, sum(d.category2_count) as cate2,"
				+" sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) "
				+"where SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)=4 "
				+"GROUP by TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	/**
	 * 50대 인기카테고리 반환 메서드
	 * @return 50대 카테고리 조회수 반환
	 */
	public ArrayList<Integer> getAge50Select() {
		String sql = "select sum(d.category1_count) as cate1, sum(d.category2_count) as cate2,"
				+" sum(d.category3_count) as cate3, sum(d.category4_count) as cate4 "
				+"from member m join datacenter d using(member_id) "
				+"where SUBSTR(TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1)=5 "
				+"GROUP by TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(m.MEMBER_BIRTH,'YYYY-MM-DD')) / 12),1,1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			conn = JdbcTemplate.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("cate1"));
				list.add(rs.getInt("cate2"));
				list.add(rs.getInt("cate3"));
				list.add(rs.getInt("cate4"));
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
	};
	
}
