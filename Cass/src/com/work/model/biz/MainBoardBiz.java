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
import com.work.model.dto.ReviewDto;
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
	/** 메인카테고리별 게시글 목록 가저오기  */
	public void getBoardListforMc(ArrayList<MainBoardDto> list, int pnum, String mcategoryNum) {
		Connection conn = JdbcTemplate.getConnection();
		if(pnum != 0) {
			int firstnum = 10*(pnum-1)+1;
			int lastnum = 10*pnum;
			try {
				dao.getBoardListForMc(conn,list,firstnum,lastnum,mcategoryNum);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcTemplate.close(conn);
			}
		}
	}
	/** 서브카테고리별 게시글 목록 가저오기  */
	public void getBoardListforSc(ArrayList<MainBoardDto> list, int pnum, String scategoryNum) {
		Connection conn = JdbcTemplate.getConnection();
		if(pnum != 0) {
			int firstnum = 10*(pnum-1)+1;
			int lastnum = 10*pnum;
			try {
				dao.getBoardListForSc(conn,list,firstnum,lastnum,scategoryNum);
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
	
	/** 게시글 삭제 
	 * @param dto */ 
	public void boardDelete(MainBoardDto dto) {
		
	}
	/** 게시글 상세
	 * @param dto */
	public void boardDetail(MainBoardDto dto) {
		
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardDetail(conn, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(conn);
		}
	}
	/** 게시글 수정
	 * @param dto */
	public void boardUpdate(MainBoardDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardUpdate(conn,dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		}finally {
			JdbcTemplate.close(conn);
		}
	}
	/** 게시글 검색
	 * @param dto */
	public void boardFind() {
		
	}
	/** 해당 페이지 리뷰 목록  */
	public void getReviewList(ArrayList<ReviewDto> list, int pnum, String mboardNum) {
		Connection conn = JdbcTemplate.getConnection();
		if(pnum != 0) {
				int firstnum = 10*(pnum-1)+1;
				int lastnum = 10*pnum;
			try {
				dao.getReviewList(conn,list,mboardNum,firstnum,lastnum);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JdbcTemplate.close(conn);
			}
		}
	}
	/** 리뷰 갯수  
	 * @param mboardnum */
	public void getReviewCounter(int reviewcounter, String mboardNum) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.reviewcount(conn,reviewcounter,mboardNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(conn);
		}
		
	}
	public void inputReview(ReviewDto dto) {

		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.inputReview(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	
}
