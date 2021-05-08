package com.work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.QboardBiz;
import com.work.model.dto.AboardDto;
import com.work.model.dto.MasterMemberDto;
import com.work.model.dto.QboardDto;

/**
 * @author 박민주
 * Q&A 게시판 관리 서블릿
 */
@WebServlet(urlPatterns = {"/cass/qboardController"})
public class QboardControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;
	
	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
		case "qboardList":
			qboardList(request, response);
			break;
		case "qboardDetail":
			qboardDetail(request, response);
			break;
		case "addQboard":
			addQboard(request, response);
			break;
		case "deleteQboard":
			deleteQboard(request, response);
			break;
		case "searchQboard":
			searchQboard(request, response);
			break;
		case "updateQboard":
			updateQboard(request, response);
			break;
		case "updateQboard2":
			updateQboard2(request, response);
			break;
		case "addAboard":
			addAboard(request, response);
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/** Q&A 게시판 게시글 전체 조회*/
	protected void qboardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html; charset=UTF-8");

		QboardBiz biz = new QboardBiz();

		ArrayList<QboardDto> list = biz.getQboardList();
		request.setAttribute("qboardList", list);
		request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
	}
	
	/** Q&A 게시판 게시글 상세 조회*/
	protected void qboardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardNum = request.getParameter("qboardNum");
		QboardBiz biz = new QboardBiz();

		ArrayList<QboardDto> list = biz.getQboardDetail(qboardNum);
		ArrayList<AboardDto> list2 = biz.getAboardList(qboardNum);
		request.setAttribute("qboardDetail", list);
		request.setAttribute("aboardList", list2);
		request.getRequestDispatcher("/qnaBoard/qboardDetail.jsp").forward(request, response);
	}
	
	/** Q&A 게시판 답글 등록*/
	protected void addAboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardNum = request.getParameter("qboardNum");
		String aboardContent = request.getParameter("reply");
		QboardBiz biz = new QboardBiz();
		
		aboardContent = aboardContent.trim();
		if(aboardContent == null || aboardContent.trim().length() == 0) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script language='javascript'>");
			out.println("alert('내용을 입력해주세요.')");
			out.println("window.history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}else {
			ArrayList<QboardDto> list = biz.getQboardDetail(qboardNum);
			ArrayList<AboardDto> list3 = biz.addAboard(qboardNum, aboardContent);
			ArrayList<AboardDto> list2 = biz.getAboardList(qboardNum);
			request.setAttribute("qboardDetail", list);
			request.setAttribute("aboardList", list2);
			request.getRequestDispatcher("/qnaBoard/qboardDetail.jsp").forward(request, response);	
		}
	}
	
	/** Q&A 게시판 게시글 등록*/
	protected void addQboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardTitle = request.getParameter("qboardTitle");
		String qboardContent = request.getParameter("qboardContent");
		String qboardImg = request.getParameter("qboardImg");
		HttpSession session = request.getSession(false);
		String memberId = ((MasterMemberDto)session.getAttribute("dto")).getMemberId();		
		QboardBiz biz = new QboardBiz();

		if(qboardTitle == null || qboardTitle.trim().length() == 0) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script language='javascript'>");
			out.println("alert('제목을 입력해주세요.')");
			out.println("window.history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}
		else if(qboardContent == null || qboardContent.trim().length() == 0) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			out.println("<script language='javascript'>");
			out.println("alert('내용을 입력해주세요.')");
			out.println("window.history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		}else {
			int result=biz.addQboard(qboardTitle, qboardContent, qboardImg, memberId);

			if(result == 1) {
				ArrayList<QboardDto> list = biz.getQboardList();
				String qboardNum = list.get(0).getQboardNum();
				ArrayList<QboardDto> detailQboard = biz.getQboardDetail(qboardNum);
				request.setAttribute("qboardDetail", detailQboard);
				request.getRequestDispatcher("/qnaBoard/qboardDetail.jsp").forward(request, response);
			}
		}
	}

	/** Q&A 게시판 게시글 삭제*/
	protected void deleteQboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardNum = request.getParameter("qboardNum");
		QboardBiz biz = new QboardBiz();
		
		int result = biz.deleteQboard(qboardNum);
		
		if(result == 1) {
			ArrayList<QboardDto> list = biz.getQboardList();
			request.setAttribute("qboardList", list);
			request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
		}

	}
	
	/** Q&A 게시판 게시글 검색(제목+내용, 제목, 내용)*/
	protected void searchQboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String selSearchOption = request.getParameter("selSearchOption");
		String txtKeyWord = request.getParameter("txtKeyWord");
		QboardBiz biz = new QboardBiz();

		if(selSearchOption.equals("A")) {
			ArrayList<QboardDto> list = biz.getQboardByA(txtKeyWord);
			request.setAttribute("qboardList", list);
			request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
			
		}else if(selSearchOption.equals("T")) {
			ArrayList<QboardDto> list = biz.getQboardByT(txtKeyWord);
			request.setAttribute("qboardList", list);
			request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
			
		}else if(selSearchOption.equals("C")) {
			ArrayList<QboardDto> list = biz.getQboardByC(txtKeyWord);
			request.setAttribute("qboardList", list);
			request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
		}
	}
	
	/** Q&A 게시판 게시글 수정하기 위해 게시글 불러오기*/
	protected void updateQboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardNum = request.getParameter("qboardNum");
		QboardBiz biz = new QboardBiz();

		ArrayList<QboardDto> list = biz.getQboardDetail(qboardNum);
		request.setAttribute("qboardDetailUpdate", list);
		request.getRequestDispatcher("/qnaBoard/qboardUpdate.jsp").forward(request, response);
	}
	
	/** Q&A 게시판 게시글 수정*/
	protected void updateQboard2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String qboardNum = request.getParameter("qboardNum");
		String qboardTitle = request.getParameter("qboardTitle");
		String qboardContent = request.getParameter("qboardContent");
		String qboardImg = request.getParameter("qboardImg");
		QboardBiz biz = new QboardBiz();

		int result = biz.updateQboard(qboardNum, qboardTitle, qboardContent, qboardImg);
		if(result == 1) {
			ArrayList<QboardDto> list = biz.getQboardList();
			request.setAttribute("qboardList", list);
			request.getRequestDispatcher("/qnaBoard/qboardList.jsp").forward(request, response);
		}
	}
}
