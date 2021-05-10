package com.work.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.MainBoardBiz;
import com.work.model.biz.MemberBiz;
import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.MasterMemberDto;
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
		case "inputreview":
			inputreview(request, response);
			break;
		case "deletereview":
			deletereview(request, response);
			break;
		case "updatereview":
			updatereview(request, response);
			break;
		case "deleteMainBoard":
			deleteMainBoard(request, response);
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
		
		MainBoardBiz biz = new MainBoardBiz();
		int pnum = Integer.parseInt(pageNum);
		int maxPageNum = 1;
		HashMap<Integer, ArrayList<MainBoardDto>> boardAllList = new HashMap<Integer, ArrayList<MainBoardDto>>();
		if(mcategoryNum != null) {
			biz.getBoardListforMc(boardAllList,mcategoryNum);
			maxPageNum = boardAllList.size();
		}else if(scategoryNum != null){
			biz.getBoardListforSc(boardAllList,scategoryNum);
			maxPageNum = boardAllList.size();
		}else {
			biz.getBoardList(boardAllList);
			maxPageNum = boardAllList.size();
		}
		
		ArrayList<MainBoardDto> list = boardAllList.get(pnum);
		
		request.setAttribute("mainbaordList", list);
		request.setAttribute("maxPageNum", maxPageNum);
		request.getRequestDispatcher("/MainBoard/boardList.jsp").forward(request, response);
	}
	
	/**게시글 작성*/
	protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto masterDto = (MasterMemberDto)session.getAttribute("dto");
		String grade = masterDto.getGrade();
		if(!grade.equals("B")) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		String memberId = masterDto.getMemberId();
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
		
		dto.setBusinessId(memberId);
		dto.setMcategoryNum(mcategoryNum);
		dto.setScategoryNum(scategoryNum);
		dto.setMboardTitle(mboardTitle);
		dto.setMboardImg(bimg);
		dto.setMboardInfo(bcontent);
		

		MainBoardBiz biz = new MainBoardBiz();
		biz.boardInput(dto);
		
		response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordListform&pageNum=1");
		
	}
	
	/**게시글 작성창*/
	protected void writeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto masterDto = (MasterMemberDto)session.getAttribute("dto");
		String grade = masterDto.getGrade();
		if(!grade.equals("B")) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
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
		HttpSession session = request.getSession();
		MasterMemberDto dto = (MasterMemberDto)session.getAttribute("dto");
		String grade = dto.getGrade();
		String memberId= request.getParameter("memberId");
		if(dto.getGrade().equals("G")) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		if(dto.getGrade().equals("B") && !memberId.equals(dto.getMemberId()) ) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		String mBoardNum = request.getParameter("mBoardNum");
		ArrayList<MainCategoryDto> mainCategorylist = new ArrayList<MainCategoryDto>();
		ArrayList<SubCategoryDto> subCategorylist = new ArrayList<SubCategoryDto>();

		MainBoardDto boardDto = new MainBoardDto();
		boardDto.setMboardNum(mBoardNum);
		MainBoardBiz biz = new MainBoardBiz();
		biz.getMainCategoryList(mainCategorylist);
		biz.getSubCategoryList(subCategorylist);
		biz.boardDetail(boardDto);
		
		if(mainCategorylist != null && mainCategorylist.size() >0) {
			request.setAttribute("MainBoardDto", boardDto);
			request.setAttribute("mainCategorylist", mainCategorylist);
			request.setAttribute("subCategorylist", subCategorylist);
			request.getRequestDispatcher("/MainBoard/UpdateWrite.jsp").forward(request, response);
		}
	}
	
	/**게시글 수정*/
	protected void upDateWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto dto = (MasterMemberDto)session.getAttribute("dto");
		String grade = dto.getGrade();
		if(!(grade.equals("B")||grade.equals("A"))) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		
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
		MainBoardDto  boardDto = new MainBoardDto();
		bimg = bimg.trim();
		if(mbimg != null && mbimg.length() != 0) {
			mcontent = null;
			mbimg = "/Image/"+mbimg.trim();
			boardDto.setMboardContent(mbimg);
		}else {
			mcontent = "/text/" + mcontent;
			boardDto.setMboardContent(mcontent);
		}
		boardDto.setMboardNum(mboardNum);
		boardDto.setMcategoryNum(mcategoryNum);
		boardDto.setScategoryNum(scategoryNum);
		boardDto.setMboardTitle(mboardTitle);
		boardDto.setMboardImg(bimg);
		boardDto.setMboardInfo(bcontent);
		
		MainBoardBiz biz = new MainBoardBiz();
		biz.boardUpdate(boardDto);
		
		response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&pageNum=1&mBoardNum="+mboardNum);
		}
	
	/**게시글  상세보기*/
	protected void mainbaordDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mboardNum = request.getParameter("mBoardNum");
		HttpSession session = request.getSession();
		MasterMemberDto dto = (MasterMemberDto) session.getAttribute("dto");
		MainBoardDto  boardDto = new MainBoardDto();
		boardDto.setMboardNum(mboardNum);
		MainBoardBiz biz = new MainBoardBiz();
		biz.boardDetail(boardDto);
		if(dto instanceof MemberDto) {
			biz.clickTime(dto ,boardDto.getMcategoryNum());
		}
		
		if(boardDto.getMboardContent()!=null) {
			String mboardContent = boardDto.getMboardContent();
			if(!mboardContent.substring(0, 7).equals("/Image/")) {
				boardDto.setMboardContent(mboardContent.replaceAll("\r\n", "<br>"));
			}
			String pageNum = request.getParameter("pageNum");
			int pnum = Integer.parseInt(pageNum);
			HashMap<Integer, ArrayList<ReviewDto>> reviewAllList = new HashMap<Integer, ArrayList<ReviewDto>>();
			biz.getReviewList(reviewAllList,boardDto.getMboardNum());
			int reviewcounter = biz.getReviewCounter(boardDto.getMboardNum());
			int maxPageNum = 1;
			if(reviewcounter != 0) {
				if(reviewcounter%10 != 0) {
					maxPageNum = reviewcounter/10 + 1;
				}else {
					maxPageNum = reviewcounter/10;
				}
			}
			ArrayList<ReviewDto> reviewlist = reviewAllList.get(pnum);
			request.setAttribute("detaildto", boardDto);
			request.setAttribute("maxReviewPageNum", maxPageNum);
			request.setAttribute("ReviewList", reviewlist);
			request.getRequestDispatcher("/MainBoard/mainBoardDetail.jsp").forward(request, response);
		}
	}
	
	/** 게시글 삭제*/
	protected void deleteMainBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mboardNum = request.getParameter("mboardNum");
		String memberId = request.getParameter("memberId");
		HttpSession session = request.getSession();
		MasterMemberDto dto = (MasterMemberDto)session.getAttribute("dto");
		if(dto.getGrade().equals("G")) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		if(dto.getGrade().equals("B") && !memberId.equals(dto.getMemberId()) ) {
			response.sendRedirect(CONTEXT_PATH+"/welcome.jsp");
			return;
		}
		if(mboardNum!=null) {
			MainBoardDto mainBoardDto = new MainBoardDto();
			mainBoardDto.setMboardNum(mboardNum);
			MainBoardBiz biz = new MainBoardBiz();
			biz.boardDelete(mainBoardDto);
			
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordListform&pageNum=1");
			}
	}
	
	/** 리뷰 등록 */
	protected void inputreview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto masterDto = (MasterMemberDto)session.getAttribute("dto");
		String mboardNum = request.getParameter("mboardNum");
		String grade = masterDto.getGrade();
		if(grade.equals("B")) {
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
			return;
		}
		String memberId = masterDto.getMemberId();
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
		MemberDto memberDto = (MemberDto)masterDto;
		if(!biz.checkreview(memberId, mboardNum)) {
			int point = 100;
			if(reviewImg.length() >3) {
				point = 300;
			}
			memberDto.setPoint(memberDto.getPoint()+point);
			MemberBiz memberBiz = new MemberBiz();
			try {
				memberBiz.pointModify(memberDto);
				session.setAttribute("dto", memberDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		biz.inputReview(dto);
		
		
		
		response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
	}
	
	/** 리뷰 수정 */
	protected void updatereview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto masterDto = (MasterMemberDto)session.getAttribute("dto");
		String mboardNum = request.getParameter("mboardNum");
		String grade = masterDto.getGrade();
		if(grade.equals("B")) {
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
			return;
		}
		String memberId = request.getParameter("memberId");
		if(!memberId.equals(masterDto.getMemberId())) {
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
			return;
		}
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		String reviewContent = request.getParameter("reviewTextarea");
		ReviewDto dto = new ReviewDto();
		dto.setReviewNum(reviewNum);
		dto.setReviewContent(reviewContent);
		
		MainBoardBiz biz = new MainBoardBiz();
		biz.updatereview(dto);
		
		response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
	}
	
	/** 리뷰 삭제 */
	protected void deletereview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MasterMemberDto masterDto = (MasterMemberDto)session.getAttribute("dto");
		String mboardNum = request.getParameter("mboardNum");
		System.out.println(mboardNum);
		String grade = masterDto.getGrade();
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		if(grade.equals("B")) {
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
			return;
		}
		if(!memberId.equals(masterDto.getMemberId())) {
			response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
			return;
		}
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		ReviewDto dto = new ReviewDto();
		dto.setMboardNum(mboardNum);
		dto.setMemberId(memberId);
		dto.setReviewNum(reviewNum);
		
		MainBoardBiz biz = new MainBoardBiz();
		biz.deletereview(dto);
		
		response.sendRedirect(CONTEXT_PATH+"/MainBoard/mainboardController?action=mainbaordDetail&mBoardNum="+mboardNum+"&pageNum=1");
	}
}
