/**
 * 
 */
package com.work.model.biz;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.PointDao;
import com.work.model.dto.PointDto;

/**
 * @author 최아연
 * 포인트 상품게시판 Biz
 */
public class PointBiz {

	private PointDao dao = PointDao.getInstance();

	/**
	 * 포인트 상품 등록
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
	 * 포인트 상품 전체조회
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
	 * 포인트 상세조회
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

	/**
	 * 포인트 게시글 수정
	 * @param pointDto
	 * @param pboardNum
	 * @throws Exception 
	 */
	public void pointUpdate(PointDto pointDto, String pboardNum) throws Exception {
		Connection con = JdbcTemplate.getConnection();
		try {
			dao.pointUpdate(con, pointDto, pboardNum);
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
	 * 포인트 게시글 삭제
	 * @param pboardNum
	 * @throws Exception 
	 */
	public void pointDelete(String pboardNum) throws Exception {
		Connection con = JdbcTemplate.getConnection();

		try {
			dao.pointDelete(con, pboardNum);
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
	 * 상품명 검색
	 * @param pointlist
	 * @param searchName
	 * @throws IOException 
	 */
	public void titlesearch(ArrayList<PointDto> pointlist, String searchName) throws IOException {
		Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.titlesearch(con, pointlist, searchName);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
	}

	/**
	 * 상품가격 검색
	 * @param pointlist
	 * @param searchName
	 * @throws IOException 
	 */
	public void pricesearch(ArrayList<PointDto> pointlist, String searchName) throws IOException {
        Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.pricesearch(con, pointlist, searchName);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}

	/**
	 * 메인 카테고리  검색
	 * @param pointlist
	 * @param mcategoryNum
	 * @throws IOException 
	 */
	public void mcategorysearch(ArrayList<PointDto> pointlist, String mcategoryNum) throws IOException {
        Connection con = JdbcTemplate.getConnection();
		
		try {
			dao.mcategorysearch(con, pointlist, mcategoryNum);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JdbcTemplate.close(con);
		}
		
	}
}
