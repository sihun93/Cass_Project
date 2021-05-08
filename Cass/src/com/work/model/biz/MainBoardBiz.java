/**
 * 
 */
package com.work.model.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.work.model.dao.JdbcTemplate;
import com.work.model.dao.MainBoardDao;
import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.MasterMemberDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;
import com.work.util.Utility;

/**
 * @author 김성재
 *
 */
public class MainBoardBiz {
	MainBoardDao dao = MainBoardDao.getInstance();

	/**
	 * 게시글 작성
	 * 
	 * @param dto
	 */
	public void boardInput(MainBoardDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardInput(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/** 메인 카테고리 목록 획득 */
	public void getMainCategoryList(ArrayList<MainCategoryDto> mainCategorylist) {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.getMainCategoryList(conn, mainCategorylist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}

	}

	/** 서브 카테고리 목록 획득 */
	public void getSubCategoryList(ArrayList<SubCategoryDto> subCategorylist) {
		Connection conn = JdbcTemplate.getConnection();

		try {
			dao.getSubCategoryList(conn, subCategorylist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}

	}

	/**
	 * 게시글 목록 가저오기
	 * 
	 * @param list
	 * @param pageNum
	 */
	public void getBoardList(HashMap<Integer, ArrayList<MainBoardDto>> boardAllList) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getBoardList(conn, boardAllList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/** 메인카테고리별 게시글 목록 가저오기 */
	public void getBoardListforMc(HashMap<Integer, ArrayList<MainBoardDto>> boardAllList, String mcategoryNum) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getBoardListForMc(conn, boardAllList, mcategoryNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/** 서브카테고리별 게시글 목록 가저오기 */
	public void getBoardListforSc(HashMap<Integer, ArrayList<MainBoardDto>> boardAllList, String scategoryNum) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getBoardListForSc(conn, boardAllList, scategoryNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/** 게시글 갯수 반환 */
	public int getBoardCounter() {
		return dao.boardcount();
	}

	public int getBoardCounterMc(String mcategoryNum) {

		return dao.boardcountMc(mcategoryNum);
	}

	public int getBoardCounterSc(String scategoryNum) {

		return dao.boardcountSc(scategoryNum);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param dto
	 */
	public void boardDelete(MainBoardDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.deletereview(conn, dto);
			dao.boardDelete(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 게시글 상세
	 * 
	 * @param dto
	 */
	public void boardDetail(MainBoardDto dto) {

		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardDetail(conn, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 게시글 수정
	 * 
	 * @param dto
	 */
	public void boardUpdate(MainBoardDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.boardUpdate(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		} finally {
			JdbcTemplate.close(conn);
		}
	}


	/** 해당 페이지 리뷰 목록 */
	public void getReviewList(HashMap<Integer, ArrayList<ReviewDto>> reviewAllList, String mboardNum) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.getReviewList(conn, reviewAllList, mboardNum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
		}
	}

	/**
	 * 리뷰 갯수
	 * 
	 * @param mboardnum
	 */
	public int getReviewCounter(String mboardNum) {
		return dao.reviewcount(mboardNum);
	}

	/** 리뷰 작성 */
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
	/** 리뷰 수정 */
	public void updatereview(ReviewDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.updatereview(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	/** 리뷰 삭제 */
	public void deletereview(ReviewDto dto) {
		Connection conn = JdbcTemplate.getConnection();
		try {
			dao.deletereview(conn, dto);
			JdbcTemplate.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JdbcTemplate.rollback(conn);
		} finally {
			JdbcTemplate.close(conn);
		}
	}
	/** 리뷰 등록 확인 */
	public boolean checkreview(String memberId, String mboardNum) {
		return dao.checkreview(memberId,mboardNum);
	}

	public void clickTime(MasterMemberDto dto,String mcategoryNum) {
		String columndate = null;
		String columncount = null;
		switch (mcategoryNum) {
		case "mc1": columndate="category1_date"; columncount="category1_count"; break;
		case "mc2": columndate="category2_date"; columncount="category2_count"; break;
		case "mc3": columndate="category3_date"; columncount="category3_count"; break;
		case "mc4": columndate="category4_date"; columncount="category4_count"; break;
		default:
			return;
		}
		String checkTime = dao.getTime(dto,columndate);
		String currentTime = Utility.getCurrentDate("HH-mm-ss");
		String[] checkTimeArray = checkTime.split("-");
		String[] currentTimeArray = currentTime.split("-");
		int checkTimeValue = Integer.parseInt(checkTimeArray[0])*60+Integer.parseInt(checkTimeArray[1]);
		int currentTimeValue = Integer.parseInt(currentTimeArray[0])*60+Integer.parseInt(currentTimeArray[1]);
		if((currentTimeValue-checkTimeValue) >=30 || ((currentTimeValue-checkTimeValue) >=-1410 && (currentTimeValue-checkTimeValue) <0)) {
			dao.setdata(dto,columncount);
		}
		
	}
}
