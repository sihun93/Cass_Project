package com.work.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.MainBoardBiz;
import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.MemberDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;

/**
 * Servlet implementation class MainboardController
 */
@WebServlet(urlPatterns = {"/MainBoard/mainboardController"})
public class MainboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//		HttpSession session = request.getSession();
//		BusinessMemberDto dto = new BusinessMemberDto("marineanimal", "marine", "434-12-25460", "바다동물원", "06288/서울특별시 강남구 삼성로 154 ", "070-5431-0364", "https://marineanimal.com");
		//session.setAttribute("dto", dto);
		switch (action) {
		case "write":
			write(request, response);
			break;
		case "writeForm":
			writeForm(request, response);
			break;
		case "upDateWrite":
			upDateWrite(request, response);
			break;
		case "upDateWriteForm":
			upDateWriteForm(request, response);
			break;
		case "mainbaordListform":
			mainbaordListform(request, response);
			break;
		case "mainbaordDetail":
			mainbaordDetail(request, response);
			break;
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	/**게시글 목록*/
	protected void mainbaordListform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mcategoryNum = request.getParameter("mcategoryNum");
		String scategoryNum = request.getParameter("scategoryNum");
		String pageNum = request.getParameter("pageNum");
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		MainBoardBiz biz = new MainBoardBiz();
		int pnum = Integer.parseInt(pageNum);
		if(mcategoryNum != null) {
			biz.getBoardListforMc(list,pnum,mcategoryNum);
		}else if(scategoryNum != null){
			biz.getBoardListforSc(list,pnum,scategoryNum);
		}else {
			biz.getBoardList(list,pnum);
		}
		
		
		int boardcounter = 0;
		biz.getBoardCounter(boardcounter);
		int maxPageNum = 1;
		if(boardcounter != 0) {
			if(maxPageNum%10 != 0) {
				maxPageNum = boardcounter/10 + 1;
			}else {
				maxPageNum = boardcounter/10;
			}
		}
		request.setAttribute("mainbaordList", list);
		request.setAttribute("maxPageNum", maxPageNum);
		request.getRequestDispatcher("/MainBoard/boardList.jsp").forward(request, response);
	}
	
	/**게시글 작성*/
	protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String businessId = (String) session.getAttribute("dto");
		String mcategoryNum = request.getParameter("mcategory");
		String scategoryNum = request.getParameter("scategory");
		String mboardTitle = request.getParameter("title");
		String bimg = request.getParameter("bimg");
		String bcontent = request.getParameter("data1")+"\\"+
				request.getParameter("data2")+"\\"+
				request.getParameter("data3")+"\\"+
				request.getParameter("data4");
		String mbimg = request.getParameter("mbimg");
		String mcontent = request.getParameter("mcontent");
		
		
		MainBoardDto  dto = new MainBoardDto();
		bimg = bimg.trim();
		if(mbimg != null && mbimg.length() != 0) {
			mcontent = null;
			mbimg = "/Image/"+mbimg.trim();
			dto.setMboardContent(mbimg);
		}else {
			mcontent = "/text/" + mcontent;
			dto.setMboardContent(mcontent);
		}
		
		dto.setBusinessId(businessId);
		dto.setMcategoryNum(mcategoryNum);
		dto.setScategoryNum(scategoryNum);
		dto.setMboardTitle(mboardTitle);
		dto.setMboardImg(bimg);
		dto.setMboardInfo(bcontent);
		

		MainBoardBiz biz = new MainBoardBiz();
		biz.boardInput(dto);
		
		request.setAttribute("action", "mainbaordListform");
		request.setAttribute("pageNum", "1");
		request.getRequestDispatcher("/MainBoard/mainboardController").forward(request, response);
	
		
	}
	
	/**게시글 작성창*/
	protected void writeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MainCategoryDto> mainCategorylist = new ArrayList<MainCategoryDto>();
		ArrayList<SubCategoryDto> subCategorylist = new ArrayList<SubCategoryDto>();
		
		MainBoardBiz biz = new MainBoardBiz();
		biz.getMainCategoryList(mainCategorylist);
		biz.getSubCategoryList(subCategorylist);
		if(mainCategorylist != null && mainCategorylist.size() >0) {
			request.setAttribute("mainCategorylist", mainCategorylist);
			request.setAttribute("subCategorylist", subCategorylist);
			request.getRequestDispatcher("/MainBoard/Write.jsp").forward(request, response);
		}
	}
	
	/**게시글 수정 페이지*/
	protected void upDateWriteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mBoardNum = request.getParameter("mBoardNum");
		ArrayList<MainCategoryDto> mainCategorylist = new ArrayList<MainCategoryDto>();
		ArrayList<SubCategoryDto> subCategorylist = new ArrayList<SubCategoryDto>();

		MainBoardDto dto = new MainBoardDto();
		dto.setMboardNum(mBoardNum);
		MainBoardBiz biz = new MainBoardBiz();
		biz.getMainCategoryList(mainCategorylist);
		biz.getSubCategoryList(subCategorylist);
		biz.boardDetail(dto);
		
		if(mainCategorylist != null && mainCategorylist.size() >0) {
			request.setAttribute("MainBoardDto", dto);
			request.setAttribute("mainCategorylist", mainCategorylist);
			request.setAttribute("subCategorylist", subCategorylist);
			request.getRequestDispatcher("/MainBoard/UpdateWrite.jsp").forward(request, response);
		}
	}
	
	/**게시글 수정*/
	protected void upDateWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mboardNum = request.getParameter("mboardNum");
		String mcategoryNum = request.getParameter("mcategory");
		String scategoryNum = request.getParameter("scategory");
		String mboardTitle = request.getParameter("title");
		String bimg = request.getParameter("bimgval");
		String bcontent = request.getParameter("data1")+"\\"+
				request.getParameter("data2")+"\\"+
				request.getParameter("data3")+"\\"+
				request.getParameter("data4");
		String mbimg = request.getParameter("mbimgval");
		String mcontent = request.getParameter("mcontent");
		
		System.out.println(bimg+","+mbimg);
		MainBoardDto  dto = new MainBoardDto();
		bimg = bimg.trim();
		if(mbimg != null && mbimg.length() != 0) {
			mcontent = null;
			mbimg = "/Image/"+mbimg.trim();
			dto.setMboardContent(mbimg);
		}else {
			mcontent = "/text/" + mcontent;
			dto.setMboardContent(mcontent);
		}
		dto.setMboardNum(mboardNum);
		dto.setMcategoryNum(mcategoryNum);
		dto.setScategoryNum(scategoryNum);
		dto.setMboardTitle(mboardTitle);
		dto.setMboardImg(bimg);
		dto.setMboardInfo(bcontent);
		
		MainBoardBiz biz = new MainBoardBiz();
		biz.boardUpdate(dto);
		
		request.setAttribute("action", "mainbaordDetail");
		request.setAttribute("mBoardNum", mboardNum);
		request.setAttribute("pageNum", "1");
		request.getRequestDispatcher("/MainBoard/mainboardController").forward(request, response);
	
	}
	
	/**게시글  상세보기*/
	protected void mainbaordDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mboardNum = request.getParameter("mBoardNum");

		MainBoardDto  dto = new MainBoardDto();
		dto.setMboardNum(mboardNum);
		MainBoardBiz biz = new MainBoardBiz();
		biz.boardDetail(dto);

		if(dto.getMboardContent()!=null) {
			String mboardContent = dto.getMboardContent();
			if(!mboardContent.substring(0, 7).equals("/Image/")) {
				dto.setMboardContent(mboardContent.replaceAll("\r\n", "<br>"));
			}
			
			String pageNum = request.getParameter("pageNum");
			int pnum = Integer.parseInt(pageNum);
			ArrayList<ReviewDto> list = new ArrayList<ReviewDto>();
			biz.getReviewList(list,pnum,dto.getMboardNum());
			int reviewcounter = 0;
			biz.getReviewCounter(reviewcounter,dto.getMboardNum());
			int maxPageNum = 1;
			if(reviewcounter != 0) {
				if(maxPageNum%10 != 0) {
					maxPageNum = reviewcounter/10 + 1;
				}else {
					maxPageNum = reviewcounter/10;
				}
			}
			
			
			request.setAttribute("detaildto", dto);
			request.setAttribute("maxReviewPageNum", maxPageNum);
			request.setAttribute("ReviewList", list);
			request.getRequestDispatcher("/MainBoard/mainBoardDetail.jsp").forward(request, response);
		}
	}
	/** 리뷰 등록 */
	protected void inputreview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = ((MemberDto)session.getAttribute("dto")).getMemberId();
		String mboardNum = request.getParameter("mboardNum");
		int score = Integer.parseInt(request.getParameter("reviewscore"));
		String reviewContent = request.getParameter("reviewTextarea");
		String[] reviewImgArray = request.getParameterValues("reviewimg");
		String reviewImg ="";
		for(String a :reviewImgArray) {
			reviewImg = reviewImg + a+ "\\";
		}
		ReviewDto dto = new ReviewDto();
		dto.setMemberId(memberId);
		dto.setMboardNum(mboardNum);
		dto.setScore(score);
		dto.setReviewContent(reviewContent);
		dto.setReviewImg(reviewImg);
		MainBoardBiz biz = new MainBoardBiz();
		biz.inputReview(dto);
		
		request.setAttribute("action", "mainbaordDetail");
		request.setAttribute("mBoardNum", mboardNum);
		request.setAttribute("pageNum", "1");
		request.getRequestDispatcher("/MainBoard/mainboardController").forward(request, response);
	}
}
