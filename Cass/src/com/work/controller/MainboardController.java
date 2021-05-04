package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.work.model.biz.MainBoardBiz;
import com.work.model.dto.MainBoardDto;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.SubCategoryDto;

/**
 * Servlet implementation class MainboardController
 */
@WebServlet(urlPatterns = {"/MainBoard/mainboardController"}, loadOnStartup = 1)
public class MainboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();
		application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		switch (action) {
		case "write":
			write(request, response);
			break;
		case "writeform":
			writeform(request, response);
			break;

		default:
			break;
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String businessId = "cathost";
		String mcategoryNum = request.getParameter("mcategory");
		String scategoryNum = request.getParameter("scategory");
		String mboardTitle = request.getParameter("title");
		String bimg = request.getParameter("bimg");
		String bcontent = request.getParameter("bcontent");
		String mbimg = request.getParameter("mbimg");
		String mcontent = request.getParameter("mcontent");
		

		MainBoardDto  dto = new MainBoardDto();
		bcontent = bcontent.replaceAll("\r\n", "<br>");
		bimg = "https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F"+businessId+"%2F"+bimg.trim()+"?alt=media";
		if(mbimg != null && mbimg.length() != 0) {
			mcontent = null;
			mbimg = "https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/mainboard%2F"+businessId+"%2F"+mbimg.trim()+"?alt=media";
			dto.setMboardContent(mbimg);
		}else {
			dto.setMboardContent(mcontent);
		}
		
		dto.setBusinessId(businessId);
		dto.setMcategoryNum(mcategoryNum);
		dto.setScategoryNum(scategoryNum);
		dto.setMboardTitle(mboardTitle);
		dto.setMboardImg(bimg);
		dto.setMboardInfo(bcontent);
		

		System.out.println(dto);
		MainBoardBiz biz = new MainBoardBiz();
		biz.boardInput(dto);
		
		
	}
	
	protected void writeform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void mainbaordListform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MainCategoryDto> mainCategorylist = new ArrayList<MainCategoryDto>();
		ArrayList<SubCategoryDto> subCategorylist = new ArrayList<SubCategoryDto>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		ArrayList<MainBoardDto> list = new ArrayList<MainBoardDto>();
		MainBoardBiz biz = new MainBoardBiz();
		biz.getBoardList(list,pageNum);
		
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

}
