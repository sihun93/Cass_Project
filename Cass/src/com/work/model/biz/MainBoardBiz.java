/**
 * 
 */
package com.work.model.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.MainBoardDao;
import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.SubCategoryDto;

/**
 * @author 김성재
 *
 */
public class MainBoardBiz {
	MainBoardDao dao = MainBoardDao.getInstance();
	/** 게시글 작성 
	 * @param dto */
	public void boardInput(MainBoardDto dto) {
//		Connection conn = JdbcTemplate.getConnection();
//		try {
//			dao.boardInput(conn,dto);
//			JdbcTemplate.commit(conn);
//		} catch (Exception e) {
//			JdbcTemplate.rollback(conn);
//			e.printStackTrace();
//		}finally {
//			JdbcTemplate.close(conn);
//		}
			try {
				dao.boardInput(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/** 메인 카테고리 목록 획득 */
	public void getMainCategoryList(ArrayList<MainCategoryDto> mainCategorylist) {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.getMainCategoryList(conn,mainCategorylist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(conn);
		}

	}
	/** 서브 카테고리 목록 획득 */
	public void getSubCategoryList(ArrayList<SubCategoryDto> subCategorylist) {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.getSubCategoryList(conn,subCategorylist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(conn);
		}

	}
	/** 게시글 목록 가저오기 
	 * @param list 
	 * @param pageNum */
	public void getBoardList(ArrayList<MainBoardDto> list, int pageNum) {
		Connection conn = JdbcTemplate.getConnection();
		if(pageNum != 0) {
			int firstnum = 10*(pageNum-1)+1;
			int lastnum = 10*pageNum;
			try {
				dao.getBoardList(conn,list,firstnum,lastnum);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcTemplate.close(conn);
			}
		}
	}
	/** 게시글 갯수 반환 
	 * @param list */
	public void getBoardCounter(int counter) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardcount(conn,counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(conn);
		}
	}
	
	/** 게시글 삭제 */ 
	public void boardDelete() {
		
	}
	/** 게시글 상세*/
	public void boardDetail() {
		
	}
	/** 게시글 상세*/
	public void boardUpdate() {
		
	}
	/** 게시글 상세*/
	public void boardFind() {
		
	}
	
}
