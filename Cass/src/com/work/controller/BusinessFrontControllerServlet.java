package com.work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.BusinessBiz;
import com.work.model.biz.MemberBiz;
import com.work.model.dao.CommonException;
import com.work.model.dto.BusinessMemberDto;
import com.work.model.dto.MemberDto;
import com.work.model.dto.MessageEntity;

/**
 * Servlet implementation class MemberFrontControllerServlet
 */
@WebServlet(urlPatterns = { "/business/frontController"})
public class BusinessFrontControllerServlet extends HttpServlet {
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
		System.out.println(action);
		switch (action) {
		case "businessLoginForm":
			businessLoginForm(request, response);
			break;
		case "businessInputForm":
			businessInputForm(request, response);
			break;
		case "businessFindForm":
			businessFindForm(request, response);
			break;
		case "businessFindIdForm":
			businessFindIdForm(request, response);
			break;
		case "businessFindPwForm":
			businessFindPwForm(request, response);
			break;
		case "bsLogin":
			try {
				bsLogin(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "businessInput":
			businessInput(request, response);
			break;
		case "businessInfoForm":
			businessInfoForm(request, response);
			break;
		case "businessInfoUpdate":
			try {
				businessInfoUpdate(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "businessList":
			businessList(request, response);
			break;
		case "businessIdFind":
			businessIdFind(request, response);
			break;
		case "businessPwFind":
			businessPwFind(request, response);
			break;
		case "businessDelete":
			try {
				businessDelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			process(request, response);
		}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			process(request, response);
		}
		
		/**
		 * 사업자회원 로그인페이지로 이동하는 Form
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessLoginForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/business/bsLogin.jsp");
			dispatcher.forward(request, response);
		}
		
		/**
		 * 사업자 회원가입 이동
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessInputForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/business/businessInput.jsp");
			dispatcher.forward(request, response);
		}
		
		
		/**
		 * 사업자 아이디/비밀번호 찾기 선택
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessFindForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/business/businessFind.jsp");
			dispatcher.forward(request, response);
		}
		
		
		/**
		 * 사업자 아이디 찾기 Form
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessFindIdForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/business/businessFindId.jsp");
			dispatcher.forward(request, response);
		}
		
		/**
		 * 사업자 비밀번호 찾기 Form
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessFindPwForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/business/businessFindPw.jsp");
			dispatcher.forward(request, response);
		}

		
		/**
		 * 사업자 회원 로그인
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws CommonException
		 */
		protected void bsLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommonException {
			String businessId = request.getParameter("businessId");
			String businessPw = request.getParameter("businessPw");
			
			if(businessId.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			if (businessPw.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 1);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			businessId = businessId.trim();
			businessPw = businessPw.trim();
			BusinessBiz biz = new BusinessBiz();
			BusinessMemberDto bdto = new BusinessMemberDto();
			bdto.setMemberId(businessId);
			bdto.setBusinessPw(businessPw);
			System.out.println("businessId : [" + businessId + "]");
			System.out.println("businessPw : [" + businessPw + "]");
			biz.bsLogin(bdto);
			if(bdto.getBusinessNum() != null) {
				System.out.println(CONTEXT_PATH);
				HttpSession session = request.getSession();
				session.setAttribute("bdto", bdto);
				System.out.println("로그인성공");
				RequestDispatcher requestView = request.getRequestDispatcher("/welcome.jsp");
				requestView.forward(request, response);
			}else {
				MessageEntity messageEntity = new MessageEntity("error", 1);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
		}
			
		/**
		 * 로그아웃
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void logout(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			if (session != null) {
				if (session.getAttribute("bdto") != null) {
					session.removeAttribute("bdto");
				}
				session.invalidate();
			}
			String url = "/Cass/member/main.jsp";
			response.sendRedirect(url);
		}
		
		/**
		 * 사업자 회원가입	
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessInput(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String businessId = request.getParameter("businessId");
			String businessPw = request.getParameter("businessPw");
			String businessNum = request.getParameter("businessNum");
			String businessTitle = request.getParameter("businessTitle");
			String addrCode = request.getParameter("addrCode");
			String businessAddr1 = request.getParameter("businessAddr1");
			String businessAddr2 = request.getParameter("businessAddr2");
			String businessAddr = addrCode + "/" + businessAddr1 + "/" + businessAddr2;
			String businessPhone = request.getParameter("businessPhone");
			String businessHomepage = request.getParameter("businessHomepage");
			System.out.println("businessId : [" + businessId + "]");
			System.out.println("businessPw : [" + businessPw + "]");
			System.out.println("businessNum : [" + businessNum + "]");
			System.out.println("businessTitle : [" + businessTitle + "]");
			System.out.println("businessAddr : [" + businessAddr + "]");
			System.out.println("businessPhone : [" + businessPhone + "]");
			System.out.println("businessHomepage : [" + businessHomepage + "]");
			
			if(businessId.isEmpty() || businessPw.isEmpty() || businessNum.isEmpty() || businessTitle.isEmpty() || businessAddr.isEmpty() || businessPhone.isEmpty() || businessHomepage.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("error", 1);
				messageEntity.setLinkTitle("사업자 회원가입");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessInputForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			
			BusinessMemberDto bdto = new BusinessMemberDto(businessId, businessPw, businessNum, businessTitle, businessAddr, businessPhone, businessHomepage);
			BusinessBiz biz = new BusinessBiz();
			try {
				biz.addBusiness(bdto);
				System.out.println("회원가입성공");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/business/bsLogin.jsp");
				dispatcher.forward(request, response);
			}catch (CommonException e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/message/message.jsp");
				dispatcher.forward(request, response);
				MessageEntity messageEntity = e.getMessageEntity();
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessInputForm");
				messageEntity.setLinkTitle("회원가입 재시도");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
		}
		
		/**
		 * 사업자 회원 내 정보조회
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessInfoForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("bdto") == null) {
				MessageEntity messageEntity = new MessageEntity("message", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			
			String businessId = ((BusinessMemberDto)session.getAttribute("bdto")).getMemberId();
			BusinessBiz biz = new BusinessBiz();
			BusinessMemberDto bdto = new BusinessMemberDto();
			bdto.setMemberId(businessId);
			
			try {
				biz.businessInfo(bdto);
				session.setAttribute("bdto", bdto);
				request.getRequestDispatcher("/business/businessMyInfo.jsp").forward(request, response);
			} catch (CommonException e) {
				e.printStackTrace();
				MessageEntity messageEntity = e.getMessageEntity();
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				messageEntity.setLinkTitle("로그인");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
		}
		
		
		/**
		 * 사업자 회원 내 정보 수정
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws CommonException
		 */
		protected void businessInfoUpdate(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException, CommonException {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("bdto") == null) {
				MessageEntity messageEntity = new MessageEntity("message", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			String businessId = ((BusinessMemberDto)session.getAttribute("bdto")).getMemberId();
			String businessPw = request.getParameter("businessPw");
			String businessTitle = request.getParameter("businessTitle");
			String addrCode = request.getParameter("addrCode");
			String businessAddr1 = request.getParameter("businessAddr1");
			String businessAddr2 = request.getParameter("businessAddr2");
			String businessAddr = addrCode + "/" + businessAddr1 + "/" + businessAddr2;
			String businessPhone = request.getParameter("businessPhone");
			String businessHomepage = request.getParameter("businessHomepage");
			System.out.println("businessId : [" + businessId + "]");
			System.out.println("businessPw : [" + businessPw + "]");
			System.out.println("businessTitle : [" + businessTitle + "]");
			System.out.println("businessAddr : [" + businessAddr + "]");
			System.out.println("businessPhone : [" + businessPhone + "]");
			System.out.println("businessHomepage : [" + businessHomepage + "]");
			
			BusinessBiz biz = new BusinessBiz();
			BusinessMemberDto bdto = new BusinessMemberDto();
			bdto.setMemberId(businessId);
			bdto.setBusinessPw(businessPw);
			bdto.setBusinessTitle(businessTitle);
			bdto.setBusinessAddr(businessAddr);
			bdto.setBusinessPhone(businessPhone);
			bdto.setBusinessHomepage(businessHomepage);
			
			try {
				biz.bsUpdateInfo(bdto);
				session.setAttribute("bdto", bdto);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			}catch (CommonException e) {
				e.printStackTrace();
				MessageEntity messageEntity = e.getMessageEntity();
				messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
				messageEntity.setLinkTitle("메인으로");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
			
		}
		
		/**
		 * 사업자회원 전체조회
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BusinessBiz biz = new BusinessBiz();
			ArrayList<BusinessMemberDto> list = new ArrayList<BusinessMemberDto>();
			try {
				biz.businessList(list);
				request.setAttribute("list", list);
				request.getRequestDispatcher("businessList.jsp").forward(request, response);
			} catch(Exception e) {
				MessageEntity messageEntity = new MessageEntity("error", 5);
				messageEntity.setLinkTitle("메인으로");
				messageEntity.setUrl(CONTEXT_PATH +"/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
		}
		
		
		/**
		 * 사업자회원 아이디 찾기
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessIdFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String businessNum = request.getParameter("businessNum");
			String businessPhone = request.getParameter("businessPhone");
			if(businessNum.isEmpty() || businessPhone.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 6);
				messageEntity.setLinkTitle("아이디 찾기");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessFindIdForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			
			BusinessBiz biz = new BusinessBiz();
			String businessId = biz.findBsId(businessNum, businessPhone);
			if(businessId != null) {
				MessageEntity messageEntity = new MessageEntity("success", 7);
				messageEntity.setLinkTitle("아이디 찾기 성공 : [" + businessId + "] 클릭시 로그인창으로 이동됩니다.");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 13);
				messageEntity.setLinkTitle("다시 아이디 찾기");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessFindIdForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			
		}
		
		
		/**
		 * 사업자회원 비밀번호 찾기
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void businessPwFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String businessId = request.getParameter("businessId");
			String businessPhone = request.getParameter("businessPhone");
			
			if(businessId.isEmpty() || businessPhone.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 7);
				messageEntity.setLinkTitle("비밀번호조회");
				messageEntity.setUrl(CONTEXT_PATH +"/business/frontController?action=businessFindPwForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			businessId = businessId.trim();
			businessPhone = businessPhone.trim();
			
			BusinessBiz biz = new BusinessBiz();
			String businessPw = biz.findBsPw(businessId, businessPhone);
			System.out.println(businessId+ "/" +businessPhone);
			if(businessPw != null) {
				MessageEntity messageEntity = new MessageEntity("success", 8);
				messageEntity.setLinkTitle("임시 비밀번호 발급 : [ "+businessPw+" ] 클릭시 로그인창으로 이동됩니다.");
				messageEntity.setUrl(CONTEXT_PATH +"/business/frontController?action=businessLoginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				System.out.println(businessPw);
				return;
			}else {
				MessageEntity messageEntity = new MessageEntity("error", 14);
				messageEntity.setLinkTitle("다시 비밀번호 찾기");
				messageEntity.setUrl(CONTEXT_PATH + "/business/frontController?action=businessFindPwForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
		}
		
		
		
		
		/**
		 * 사업자 회원탈퇴
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws CommonException
		 */
		protected void businessDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommonException {
			String businessId = request.getParameter("businessId");
			businessId = businessId.trim();
			BusinessBiz biz = new BusinessBiz();
			int rowCnt = biz.deleteBusiness(businessId);
			
			if(rowCnt > 0) {
				ArrayList<BusinessMemberDto> list = new ArrayList<BusinessMemberDto>();
				biz.businessList(list);
				request.setAttribute("list", list);
				RequestDispatcher requestView = request.getRequestDispatcher("/welcome.jsp");
				requestView.forward(request, response);
				return;
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 15);
				messageEntity.setLinkTitle("메인으로");
				messageEntity.setUrl(CONTEXT_PATH +"/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
		}
}
