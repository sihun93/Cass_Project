/**
 * 
 */
package com.work.model.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.PointDto;


/**
 * @author 최아연
 * 마일리지 Dao
 */
public class PointDao {

	private static PointDao instance = new PointDao(); 
	
	public PointDao() {
	}
	
	public static PointDao getInstance() { 
		return instance;
	}

	/**
	 * 마일리지 상품 등록
	 * @param con
	 * @param pointDto
	 * @param fileRealName 
	 * @param fileName 
	 * @throws Exception 
	 */
	public void pointInput(Connection con, PointDto pointDto) throws Exception {
		String sql = "insert into point_board values('p' || pboard_num_seq.nextval,?,?,?,?,?)";

		PreparedStatement stmt = null;
		StringBuffer sb=new StringBuffer();
		String content = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pointDto.getMcategoryNum());
			stmt.setString(2, pointDto.getPboardTitle());
			stmt.setString(3, pointDto.getPboardImg());
			sb.append(pointDto.getPboardContent());
			content = sb.toString();
			stmt.setString(4, content);
			stmt.setInt(5, pointDto.getPboardPrice());
			int row = stmt.executeUpdate();
			if (row == 0) {
				
				throw new Exception();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			//MessageEntity message = new MessageEntity("error", 5);
			//throw new CommonException(message);
		} finally {
			JdbcTemplate.close(stmt);		
		}		
	}

	/**
	 * 마일리지 상품 전체조회
	 * @param con
	 * @param pointlist
	 * @throws IOException 
	 * UTL_RAW.CAST_TO_VARCHAR2(
	 */
	public void pointList(Connection con, ArrayList<PointDto> pointlist) throws IOException {
		String sql = "select * from point_board"; 
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sb=new StringBuffer();
		
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
		
			     Reader input = rs.getCharacterStream("pboard_content");
			     char[] buffer = new char[1024];
			     int byteRead;
			     while((byteRead=input.read(buffer,0,1024))!=-1){
			     sb.append(buffer,0,byteRead);
			     }

				pointDto.setPboardContent(sb);
				pointDto.setPboardPrice(rs.getInt("pboard_price"));
				
				pointlist.add(pointDto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			//MessageEntity message = new MessageEntity("error", 5);
			//throw new CommonException(message);
		} finally {
			JdbcTemplate.close(stmt);
			JdbcTemplate.close(rs);
		}		
	}

	/**
	 * 마일리지 상세조회
	 * @param con
	 * @param pointDto
	 * @param pboardNum
	 * @throws IOException 
	 */
	public void pointInfo(Connection con, PointDto pointDto, String pboardNum) throws IOException {
		String sql = "select * from point_board where pboard_num=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sb=new StringBuffer();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, pboardNum);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) { 	
				pointDto.setPboardNum(rs.getString("pboard_num"));
				pointDto.setMcategoryNum(rs.getString("mcategory_num"));
				pointDto.setPboardTitle(rs.getString("pboard_title"));
				pointDto.setPboardImg(rs.getString("pboard_img"));
				
				 Reader input = rs.getCharacterStream("pboard_content");
			     char[] buffer = new char[1024];
			     int byteRead;
			     while((byteRead=input.read(buffer,0,1024))!=-1){
			     sb.append(buffer,0,byteRead);
			     }
			     
				pointDto.setPboardContent(sb);
				pointDto.setPboardPrice(rs.getInt("pboard_price"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			//MessageEntity message = new MessageEntity("error", 11);
			//throw new CommonException(message);
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(stmt);
		}
	}
	
	
}
