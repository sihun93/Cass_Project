/**
 * 
 */
package com.work.model.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.PointDto;


/**
 * @author 최아연
 * 포인트 상품게시판 Dao
 */
public class PointDao {

	private static PointDao instance = new PointDao(); 
	
	public PointDao() {
	}
	
	public static PointDao getInstance() { 
		return instance;
	}

	/**
	 * 포인트 상품 등록
	 * @param con
	 * @param pointDto
	 * @param fileRealName 
	 * @param fileName 
	 * @throws Exception 
	 */
	public void pointInput(Connection con, PointDto pointDto) throws Exception {
		String sql = "insert into point_board values('p' || pboard_num_seq.nextval,?,?,?,?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pointDto.getMcategoryNum());
			stmt.setString(2, pointDto.getPboardTitle());
			stmt.setString(3, pointDto.getPboardImg());
			stmt.setString(4, pointDto.getPboardContent());
			stmt.setInt(5, pointDto.getPboardPrice());
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
	 * 포인트 상품 전체조회
	 * @param con
	 * @param pointlist
	 * @throws IOException 
	 */
	public void pointList(Connection con, ArrayList<PointDto> pointlist) throws IOException {
		String sql = "select * from point_board order by pboard_num desc"; 
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			PointDto pointDto = null;
			while (rs.next()) {
				pointDto = new PointDto();
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				pointDto.setPboardContent(rs.getString("pboard_content"));
				pointDto.setPboardPrice(rs.getInt("pboard_price"));
				
				pointlist.add(pointDto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
 
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(rs);
		}		
	}

	/**
	 * 포인트 상세조회
	 * @param con
	 * @param pointDto
	 * @param pboardNum
	 * @throws IOException 
	 */
	public void pointInfo(Connection con, PointDto pointDto, String pboardNum) throws IOException {
		String sql = "select * from point_board where pboard_num=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pboardNum);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) { 	
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				pointDto.setPboardContent(rs.getString("pboard_content"));
				pointDto.setPboardPrice(rs.getInt("pboard_price"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}

	/**
	 * 포인트 게시글 수정
	 * @param con
	 * @param pointDto
	 * @param pboardNum
	 * @throws Exception 
	 */
	public void pointUpdate(Connection con, PointDto pointDto, String pboardNum) throws Exception {
		String sql = "update point_board set mcategory_num=?, pboard_title=?, pboard_img=?, pboard_content=?, pboard_price=? where pboard_num=?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pointDto.getMcategoryNum());
			stmt.setString(2, pointDto.getPboardTitle());
			stmt.setString(3, pointDto.getPboardImg());
			stmt.setString(4, pointDto.getPboardContent());
			stmt.setInt(5, pointDto.getPboardPrice());
			stmt.setString(6, pboardNum);
			
			int rows = stmt.executeUpdate();
			if (rows == 0) {
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
	 * 포인트 게시글 삭제
	 * @param con
	 * @param pboardNum
	 * @throws Exception 
	 */
	public void pointDelete(Connection con, String pboardNum) throws Exception {
        String sql = "delete point_board where pboard_num =?";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pboardNum);
			
			int rows = stmt.executeUpdate();
			if (rows != 1) {
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
	 * 이름 검색기능 메서드
	 * @param con
	 * @param pointlist
	 * @param searchName
	 * @throws IOException
	 */
	public void titlesearch(Connection con, ArrayList<PointDto> pointlist, String searchName) throws IOException {
		String sql = "select * from point_board where pboard_title like ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+searchName+"%");
			rs = stmt.executeQuery();
			
			PointDto pointDto = null;
			while (rs.next()) {
				pointDto = new PointDto();
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				pointDto.setPboardContent(rs.getString("pboard_content"));
				pointDto.setPboardPrice(rs.getInt("pboard_price"));
				
				pointlist.add(pointDto);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
	}

	/**
	 * 상품가격 검색
	 * @param con
	 * @param pointlist
	 * @param searchName
	 * @throws IOException 
	 */
	public void pricesearch(Connection con, ArrayList<PointDto> pointlist, String searchName) throws IOException {
        String sql = "select * from point_board where pboard_price like ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+searchName+"%");
			rs = stmt.executeQuery();
			
			PointDto pointDto = null;
			while (rs.next()) {
				pointDto = new PointDto();
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				pointDto.setPboardContent(rs.getString("pboard_content"));
				pointDto.setPboardPrice(rs.getInt("pboard_price"));
				
				pointlist.add(pointDto);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
		
	}

	/**
	 * 메인카테고리 검색
	 * @param con
	 * @param pointlist
	 * @param mcategoryNum
	 * @throws IOException 
	 */
	public void mcategorysearch(Connection con, ArrayList<PointDto> pointlist, String mcategoryNum) throws IOException {
        String sql = "select * from point_board where mcategory_num = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mcategoryNum);
			rs = stmt.executeQuery();
			
			PointDto pointDto = null;
			while (rs.next()) {
				pointDto = new PointDto();
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				pointDto.setPboardContent(rs.getString("pboard_content"));
				pointDto.setPboardPrice(rs.getInt("pboard_price"));
				
				pointlist.add(pointDto);
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
