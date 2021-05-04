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
import javax.servlet.http.HttpSession;

import com.work.model.biz.DataCenterBiz;
import com.work.model.biz.QboardBiz;
import com.work.model.dto.QboardDto;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet(urlPatterns = {"/cass/frontController"}, loadOnStartup = 1)
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
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
		case "dataCenter":
			dataCenter(request, response);
			break;
		case "qboardList":
			qboardList(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	/**
	 * 
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
		if (m != 0 || f != 0) {
			HttpSession session = request.getSession(false);
			session.setAttribute("Count", sexCount);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp"); 
		}
	}
	
	protected void qboardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html; charset=UTF-8");

		QboardBiz biz = new QboardBiz();

		ArrayList<QboardDto> list = biz.getQboardList();
		request.setAttribute("qboardList", list);
		request.getRequestDispatcher("/qboardList.jsp").forward(request, response);
	}
	
	

}
