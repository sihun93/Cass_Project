package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.DataCenterBiz;
import com.work.model.dto.DataCenterDto;
import com.work.model.dto.DataDto;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet(urlPatterns = { "/cass/frontController" }, loadOnStartup = 1)
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = application.getContextPath();// /ucamp33
		System.out.println("[loadOnStartup]CONTEXT_PATH : " + CONTEXT_PATH);
		application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "dataCenter":
			dataCenter(request, response);
			break;
		case "selectAgeDataCenter":
			selectAgeDataCenter(request, response);
			break;
		case "selectSexDataCenter":
			selectSexDataCenter(request, response);
			break;
		case "jsonDown":
			jsonDown(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 데이터 센터 처음 들어올 때 사용자 성별, 이용자 나이, 인기카테고리 별 데이터 값을 받는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void dataCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataCenterBiz biz = new DataCenterBiz();
		int m = biz.getCountM();
		int total = biz.getCount();
		int f = total - m;
		ArrayList sexCount = new ArrayList();
		sexCount.add(m);
		sexCount.add(f);
		ArrayList<Integer> age = biz.getAgeCount();
		DataDto dto = new DataDto();
		ArrayList<Integer> list = biz.getCategoryCount();
		if (m != 0 || f != 0 || age != null) {
			HttpSession session = request.getSession(false);
			session.setAttribute("Count", sexCount);
			session.setAttribute("ageCount", age);
			session.setAttribute("categoryCount", list);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
		}
	}
	/**
	 * 이용자별 인기 카테고리에서 나이별 정보를 뽑아오는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectAgeDataCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataCenterBiz biz = new DataCenterBiz();
		String  age = request.getParameter("age");
		HttpSession session = request.getSession(false);
		switch (age) {
		case "age10":
			ArrayList<Integer> list10 = biz.get10AgeSelect();
			sessionRemove(request, response);
			session.setAttribute("selectAge", list10);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
		case "age20":
			ArrayList<Integer> list20 = biz.get20AgeSelect();
			sessionRemove(request, response);
			session.setAttribute("selectAge", list20);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
		case "age30":
			ArrayList<Integer> list30 = biz.get30AgeSelect();
			sessionRemove(request, response);
			session.setAttribute("selectAge", list30);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
		case "age40":
			ArrayList<Integer> list40 = biz.get40AgeSelect();
			sessionRemove(request, response);
			session.setAttribute("selectAge", list40);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
		case "age50":
			ArrayList<Integer> list50 = biz.get50AgeSelect();
			sessionRemove(request, response);
			session.setAttribute("selectAge", list50);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;

		default:
			break;
		}
	}
	/**
	 * 사용자 성별에따른 인기카테고리 데이터 구하는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectSexDataCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataCenterBiz biz = new DataCenterBiz();
		String  sex = request.getParameter("sex");
		System.out.println(sex);
		HttpSession session = request.getSession(false);
		if (sex.equals("M")) {
			ArrayList<Integer> list = biz.getSelectMCount();
			sessionRemove(request, response);
			session.setAttribute("selectMCount", list);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
		}
		if (sex.equals("F")) {
			ArrayList<Integer> list = biz.getSelectFCount();
			sessionRemove(request, response);
			session.setAttribute("selectFCount", list);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
		}
	}
	/**
	 * 기존의 세션을 제거해주는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void sessionRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("selectFCount");
		session.removeAttribute("selectMCount");
		session.removeAttribute("selectAge");
	}
	
	protected void jsonDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataCenterBiz biz = new DataCenterBiz();
		biz.makeJson();
	}
}
