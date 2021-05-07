package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.MainCategoryBiz;
import com.work.model.biz.MemberBiz;
import com.work.model.biz.PointBiz;
import com.work.model.biz.PointBuyBiz;
import com.work.model.dao.PointDao;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.MemberDto;
import com.work.model.dto.PointBuyDto;
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
		case "pointUpdateForm":
			pointUpdateForm(request, response);
			break;
		case "pointUpdate":
			pointUpdate(request, response);
			break;
		case "pointDelete":
			pointDelete(request, response);
			break;
		case "pointBuyForm":
			pointBuyForm(request, response);
			break;
		case "pointBuy":
			pointBuy(request, response);
			break;
		case "pointBuyList":
			pointBuyList(request, response);
			break;
		case "listsearchform":
			listsearchform(request, response);
			break;
		case "mcategorysearchform":
			mcategorysearchform(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * 메인 카테고리 검색
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void mcategorysearchform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mcategoryNum = request.getParameter("mcategoryNum");
		System.out.println(mcategoryNum);
		
		PointBiz pointBiz = new PointBiz();
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		
		MainCategoryBiz mainCategoryBiz = new MainCategoryBiz();
		ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		
		try {
			pointBiz.mcategorysearch(pointlist, mcategoryNum);
			request.setAttribute("pointlist", pointlist);
			mainCategoryBiz.categoryList(categorylist);
			request.setAttribute("categorylist", categorylist);
			request.getRequestDispatcher("/point/point_main.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}

	/**
     * 상품 검색
     * @param request
     * @param response
	 * @throws IOException 
	 * @throws ServletException 
     */
	private void listsearchform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String searchName = request.getParameter("searchName");
		
		PointBiz pointBiz = new PointBiz();
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		
		MainCategoryBiz mainCategoryBiz = new MainCategoryBiz();
		ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		
		try {
			if(search.equals("pboardTitle")) {
				pointBiz.titlesearch(pointlist, searchName);
			}else if (search.equals("pboardPrice")) {
				pointBiz.pricesearch(pointlist, searchName);
			}
            request.setAttribute("pointlist", pointlist);
    		mainCategoryBiz.categoryList(categorylist);
			request.setAttribute("categorylist", categorylist);
            RequestDispatcher nextview = request.getRequestDispatcher("/point/point_main.jsp");
			nextview.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}	
    }


	/**
	 * 상품 게시글 삭제
	 * @param request
	 * @param response
	 * @throws ServletException 
	 */
	private void pointDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pboardNum = request.getParameter("pboardNum");
		PointBiz pointBiz = new PointBiz();

		try {
			pointBiz.pointDelete(pboardNum);
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}

	/**
	 * 상품 게시글 수정
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pointUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pboardNum = request.getParameter("pboardNum");
		String mcategoryNum = request.getParameter("mcategoryNum");
		String pboardTitle = request.getParameter("pboardTitle");
		String pboardImg = request.getParameter("pboardImg");
		String pboardContent = request.getParameter("pboardContent");
		String pboardPrice = request.getParameter("pboardPrice");
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		
		if(pboardTitle == null || pboardTitle.trim().length()==0) {
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
			return;
		}
		
		if(pboardPrice == null || pboardTitle.trim().length()==0) {
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
			return;
		}
		
		if(pboardContent == null || pboardTitle.trim().length()==0) {
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
			return;
		}
		
		if(pboardImg == null) {
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
			return;
		}
		
		StringBuffer sb = new StringBuffer();
		
		PointDto pointDto = new PointDto();
		pointDto.setMcategoryNum(mcategoryNum);
		pointDto.setPboardContent(sb.append(pboardContent));
		pointDto.setPboardImg(pboardImg);
		pointDto.setPboardPrice(Integer.parseInt(pboardPrice));
		pointDto.setPboardTitle(pboardTitle);
		
		PointBiz pointBiz = new PointBiz();
		try {
			pointBiz.pointUpdate(pointDto, pboardNum);
			pointBiz.pointList(pointlist);
			request.setAttribute("pointDto", pointDto);
			request.setAttribute("pointlist", pointlist);
			request.getRequestDispatcher("/point/point_Info.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
		}
	}

	/**
	 * 포인트 게시물 수정페이지 요청
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pointUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pboardNum = request.getParameter("pboardNum");
		PointBiz pointBiz = new PointBiz();
		PointDto pointDto = new PointDto();
		
		MainCategoryBiz mainCategoryBiz = new MainCategoryBiz();
		ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		
		try {
			pointBiz.pointInfo(pointDto, pboardNum);
			System.out.println(pointDto);
			request.setAttribute("pointDto", pointDto);
			mainCategoryBiz.categoryList(categorylist);
			request.setAttribute("categorylist", categorylist);
			request.getRequestDispatcher("/point/point_Update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointUpdateForm").forward(request, response);
		}
	}

	/**
	 * 포인트 상품 상세조회 페이지 요청
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pointInfoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto  = (MemberDto) session.getAttribute("dto");
		
		String pboardNum = request.getParameter("pboardNum");
		PointBiz pointBiz = new PointBiz();
		PointDto pointDto = new PointDto();
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		try {			
			pointBiz.pointInfo(pointDto, pboardNum);
			System.out.println(pointDto);
			pointBiz.pointList(pointlist);
			request.setAttribute("pointDto", pointDto);
			request.setAttribute("pointlist", pointlist);
			request.setAttribute("memberDto", memberDto);
			request.getRequestDispatcher("/point/point_Info.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}	
	}

	/**
	 * 상품등록
	 * @throws IOException 
	 */
	private void pointInput(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mcategoryNum = request.getParameter("mcategoryNum");
		String pboardTitle = request.getParameter("pboardTitle");
		String pboardPrice = request.getParameter("pboardPrice");
		String pboardContent = request.getParameter("pboardContent");
		String pboardImg = request.getParameter("pboardImg");
		System.out.println(pboardImg);
		
		StringBuffer sb = new StringBuffer();

		if(pboardTitle == null || pboardTitle.trim().length()==0) {
			response.sendRedirect(CONTEXT_PATH+"/point/point_input.jsp");
			return;
		}
		
		if(pboardPrice == null || pboardTitle.trim().length()==0) {
			response.sendRedirect(CONTEXT_PATH+"/point/point_input.jsp");
			return;
		}
		
		if(pboardContent == null || pboardTitle.trim().length()==0) {
			response.sendRedirect(CONTEXT_PATH+"/point/point_input.jsp");
			return;
		}
		
		if(pboardImg == null) {
			response.sendRedirect(CONTEXT_PATH+"/point/point_input.jsp");
			return;
		}
		
		PointDto pointDto = new PointDto();
		pointDto.setMcategoryNum(mcategoryNum);
		pointDto.setPboardContent(sb.append(pboardContent));
		pointDto.setPboardImg(pboardImg);
		pointDto.setPboardPrice(Integer.parseInt(pboardPrice));
		pointDto.setPboardTitle(pboardTitle);
		PointBiz pointBiz = new PointBiz();
		try {
			pointBiz.pointInput(pointDto);
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(CONTEXT_PATH+"/point/point_input.jsp");
		}
	}

	/**
	 * 상품 등록 페이지 요청
	 * @throws ServletException 
	 */
	private void pointInputForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		MemberDto memberDto  = (MemberDto) session.getAttribute("dto");
		
		ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		MainCategoryBiz mainCategoryBiz = new MainCategoryBiz();
		
		try {
			mainCategoryBiz.categoryList(categorylist);
			request.setAttribute("categorylist", categorylist);
			request.setAttribute("memberDto", memberDto);
			request.getRequestDispatcher("/point/point_input.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}
	
	/**
	 * 포인트 메인페이지 요청
	 * @throws ServletException 
	 * 
	 */
	
	private void pointMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PointBiz pointBiz = new PointBiz();
		ArrayList<PointDto> pointlist = new ArrayList<PointDto>();
		MainCategoryBiz mainCategoryBiz = new MainCategoryBiz();
		ArrayList<MainCategoryDto> categorylist = new ArrayList<MainCategoryDto>();
		
		try {		
			pointBiz.pointList(pointlist);
			System.out.println(pointlist);
			request.setAttribute("pointlist", pointlist);
			mainCategoryBiz.categoryList(categorylist);
			request.setAttribute("categorylist", categorylist);
			request.getRequestDispatcher("/point/point_main.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}
	
	/**
	 * 포인트 상품 구매 내역
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pointBuyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto  = (MemberDto) session.getAttribute("dto");
		String memberId = memberDto.getMemberId();
				
		PointBuyBiz pointBuyBiz = new PointBuyBiz();

		ArrayList<PointBuyDto> pointBuylist = new ArrayList<PointBuyDto>();
		
		try {
			pointBuyBiz.buylist(memberId, pointBuylist);
			request.setAttribute("pointBuylist", pointBuylist);
			request.getRequestDispatcher("/point/point_BuyList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}

	/**
	 * 포인트 상품 구매 요청
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pointBuy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pboardNum = request.getParameter("pboardNum");
		String pboardTitle = request.getParameter("pboardTitle");
		String pboardImg = request.getParameter("pboardImg");
		String pboardCount = request.getParameter("pboardCount");
		String pboardPrice = request.getParameter("pboardPrice");
		String buyDate = request.getParameter("buyDate");
		String point = request.getParameter("point");

		int price = Integer.parseInt(pboardPrice);
		int points = Integer.parseInt(point);
		int memberPoint = (points-price);
		
		HttpSession session = request.getSession();
		MemberDto memberDto  = (MemberDto) session.getAttribute("dto");
		String memberId = memberDto.getMemberId();
		MemberBiz memberBiz = new MemberBiz();
		
		PointBuyBiz pointBuyBiz = new PointBuyBiz();
		PointBuyDto pointBuyDto = new PointBuyDto();
		pointBuyDto.setMember_id(memberId);
		pointBuyDto.setPboard_img(pboardImg);
		pointBuyDto.setPboard_num(pboardNum);
		pointBuyDto.setPboard_title(pboardTitle);
		pointBuyDto.setPboardCount(Integer.parseInt(pboardCount));
		pointBuyDto.setPboardPrice(Integer.parseInt(pboardPrice));
		pointBuyDto.setBuyDate(buyDate);
		
		ArrayList<PointBuyDto> pointBuylist = new ArrayList<PointBuyDto>();
		
		try {
			pointBuyBiz.buyinput(pointBuyDto);
			pointBuyBiz.buylist(memberId, pointBuylist);
			memberBiz.updatepoint(memberId, memberPoint);
			request.setAttribute("pointBuylist", pointBuylist);
			RequestDispatcher nextview = request.getRequestDispatcher("/point/point_BuyList.jsp");
			nextview.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointBuyForm").forward(request, response);
		}	
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
		
		HttpSession session = request.getSession(false);
		MemberBiz biz = new MemberBiz();
		MemberDto dto = new MemberDto();
		String memberId = ((MemberDto)session.getAttribute("dto")).getMemberId();	
		dto.setMemberId(memberId);
		System.out.println(memberId);
		try {			
			biz.myInfo(dto);
			pointBiz.pointInfo(pointDto, pboardNum);
			request.setAttribute("pointDto", pointDto);
			request.setAttribute("pboardCount", pboardCount);
			session.setAttribute("memberDto", dto);
			request.getRequestDispatcher("/point/point_buy.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/point/pointController?action=pointMain").forward(request, response);
		}
	}
}