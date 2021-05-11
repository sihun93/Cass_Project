package com.work.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.DataCenterBiz;
import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.DataDto;
import com.work.model.dto.MasterMemberDto;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet(urlPatterns = { "/cass/frontController" })
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletContext application;
	public String CONTEXT_PATH;

	public void init() {
		application = getServletContext();
		CONTEXT_PATH = (String) application.getAttribute("CONTEXT_PATH");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		//BusinessMemberDto dto = new BusinessMemberDto("cathost", "cat77", "245-32-52143", "냥이호텔", "07906/서울특별시 양천구 화곡로12길", "010-0251-3254", "https://cathost.com");
		//session.setAttribute("dto", dto);
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
		sessionRemove(request, response);
		int m = biz.getCountM();
		int total = biz.getCount();
		int f = total - m;
		ArrayList<Integer> sexCount = new ArrayList<Integer>();
		sexCount.add(m);
		sexCount.add(f);
		ArrayList<Integer> age = biz.getAgeCount();
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
			sessionRemove(request, response);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
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
		switch (sex) {
		case "M":
			ArrayList<Integer> list = biz.getSelectMCount();
			sessionRemove(request, response);
			session.setAttribute("selectMCount", list);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
		case "F":
			ArrayList<Integer> list2 = biz.getSelectFCount();
			sessionRemove(request, response);
			session.setAttribute("selectFCount", list2);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;

		default:
			sessionRemove(request, response);
			response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp");
			break;
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
	/**
	 * json파일 다운로드 받는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void jsonDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataCenterBiz biz = new DataCenterBiz();
		HttpSession session = request.getSession();
		MasterMemberDto dto = (MasterMemberDto)session.getAttribute("dto");
	      if(!dto.getGrade().equals("A")) {
	          if(!biz.checkboard(dto.getMemberId())) {
	             response.sendRedirect(CONTEXT_PATH + "/dataCenter.jsp?alram=on");
	             return;
	          }
	       }
		biz.makeJson();
	    String savePath = "C:\\student_ucamp33\\workspace_servlet";
	    String filename = "DataCenter.json" ;
	    String orgfilename = "DataCenter.json" ;
	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
	    try{
	        try{
	            file = new File(savePath, filename);
	            in = new FileInputStream(file);
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }
	        client = request.getHeader("User-Agent");
	        response.reset() ;
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        if(!skip){
	            if(client.indexOf("MSIE") != -1){
	                response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));
	            }else{
	                orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");
	                response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
	                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            } 
	            response.setHeader ("Content-Length", ""+file.length() );
	            os = response.getOutputStream();
	            byte b[] = new byte[(int)file.length()];
	            int leng = 0;
	             
	            while( (leng = in.read(b)) > 0 ){
	                os.write(b,0,leng);
	            }
	        }else{
	            response.setContentType("text/html;charset=UTF-8");
	            System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
	        }
	        in.close();
	        os.close();
	    }catch(Exception e){
	      e.printStackTrace();
	    }


	}
}
