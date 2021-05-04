package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.work.model.biz.PointBiz;
import com.work.model.dao.PointDao;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.PointDto;

/**
 * Point Controller
 * @author 최아연
 */
@WebServlet("/point/pointController")
public class PointControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
		System.out.println("CONTEXT_PATH : "+ CONTEXT_PATH);
	}
	 

	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
		case "pointMain":
			pointMain(request, response);
			break;
		case "pointInputForm":
			pointInputForm(request, response);
			break;
		case "pointInput":
			pointInput(request, response);
			break;
		case "pointInfoForm":
			pointInfoForm(request, response);
			break;
		case "pointBuyForm":
			pointBuyForm(request, response);
			break;
		case "pointBuy":
			pointBuy(request, response);
			break;
		}
	}
	
	/**
	 * 마일리지 상품 상세조회 페이지 요청
	 * @param request
	 * @param response
	 */
	private void pointInfoForm(HttpServletRequest request, HttpServletResponse response) {
		String pboardNum = request.getParameter("pboardNum");
		PointBiz pointBiz = new PointBiz();
		PointDto pointDto = new PointDto();
		String url = "https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/"+"point%2Ftest"+"%2F"+"point01.jpg"+"?alt=media";
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		try {			
			pointBiz.pointInfo(pointDto, pboardNum);
			System.out.println(pointDto);
			pointBiz.pointList(pointlist);
			request.setAttribute("pointDto", pointDto);
			request.setAttribute("url", url);
			request.setAttribute("pointlist", pointlist);
			request.getRequestDispatcher("/point/point_Info.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("상세조회실패");
		}
		
	}

	/**
	 * 상품등록
	 */
	private void pointInput(HttpServletRequest request, HttpServletResponse response) {
		String mcategoryNum = request.getParameter("mcategoryNum");
		String pboardTitle = request.getParameter("pboardTitle");
		String pboardPrice = request.getParameter("pboardPrice");
		String pboardContent = request.getParameter("pboardContent");
		String pboardImg = request.getParameter("pboardImg");
		System.out.println(pboardImg);
		
		StringBuffer sb = new StringBuffer();

//		if(pboardTitle == null || pboardTitle.trim().length()==0) {
//			return;
//		}
//		
//		if(pboardPrice == null || pboardTitle.trim().length()==0) {
//			return;
//		}
//		
//		if(pboardContent == null || pboardTitle.trim().length()==0) {
//			return;
//		}
//		
//		if(pboardImg == null) {
//			return;
//		}
		
		PointDto pointDto = new PointDto();
		pointDto.setMcategoryNum(mcategoryNum);
		pointDto.setPboardContent(sb.append(pboardContent));
		pointDto.setPboardImg(pboardImg);
		pointDto.setPboardPrice(Integer.parseInt(pboardPrice));
		pointDto.setPboardTitle(pboardTitle);
		
		PointBiz pointbiz = new PointBiz();
		try {
			pointbiz.pointInput(pointDto);
			request.getRequestDispatcher("/point/point_main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 상품 등록 페이지 요청
	 */
	private void pointInputForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		String url = CONTEXT_PATH+"/point/point_input.jsp";
		response.sendRedirect(url);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * 마일리지 메인페이지 요청
	 * 
	 */
	
	private void pointMain(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PointBiz pointBiz = new PointBiz();
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		
		String url = "https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/"+"point%2Ftest"+"%2F"+"point01.jpg"+"?alt=media";

		try {			
			pointBiz.pointList(pointlist);
			System.out.println(pointlist);
			request.setAttribute("pointlist", pointlist);
			request.setAttribute("url", url);
			request.getRequestDispatcher("/point/point_main.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("전체조회실패");
			//request.getRequestDispatcher("/Cass/point/point_main.jsp").forward(request, response);
		}
	}
	


	/**
	 * 마일리지 상품 구매 요청
	 */
	
	private void pointBuy(HttpServletRequest request, HttpServletResponse response) {

	}
	 

	/**
	 * 구매상세조회 페이지 요청
	 */
	
	private void pointBuyForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String pboardNum = request.getParameter("pboardNum");
		String pboardCount = request.getParameter("pboardCount");
		System.out.println("구매 갯수"+pboardCount);
		PointBiz pointBiz = new PointBiz();
		PointDto pointDto = new PointDto();
		String url = "https://firebasestorage.googleapis.com/v0/b/clever-cass.appspot.com/o/"+"point%2Ftest"+"%2F"+"point01.jpg"+"?alt=media";
		try {			
			pointBiz.pointInfo(pointDto, pboardNum);
			System.out.println(pointDto);
			request.setAttribute("pointDto", pointDto);
			request.setAttribute("pboardCount", pboardCount);
			request.setAttribute("url", url);
			request.getRequestDispatcher("/point/point_buy.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("상세조회실패");
			//request.getRequestDispatcher("/Cass/point/point_main.jsp").forward(request, response);
		}
	}
	 


}
