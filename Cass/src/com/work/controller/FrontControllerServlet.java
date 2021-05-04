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

import com.work.model.dao.CommonException;
import com.work.model.biz.DataCenterBiz;
import com.work.model.biz.MemberBiz;
import com.work.model.dto.MemberDto;
import com.work.model.dto.MessageEntity;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet(urlPatterns = { "/member/frontController", "/board/frontController" }, loadOnStartup = 1)
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
		case "loginForm":
			loginForm(request, response);
			break;
		case "login":
			try {
				login(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "logout":
			logout(request, response);
			break;
		case "memberInputForm":
			memberInputForm(request, response);
			break;
		case "memberInput":
			try {
				memberInput(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "myInfoForm":
			try {
				myInfoForm(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "myInfoUpdateSave":
			myInfoUpdateSave(request, response);
			break;
		case "findForm":
			findForm(request, response);
			break;
		case "findIdForm":
			findIdForm(request, response);
			break;
		case "findPwForm":
			findPwForm(request, response);
			break;
		case "memberList":
			memberList(request, response);
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
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void dataCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	/**
	 * 로그인페이지로 이동하는 Form
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/member/login.jsp");
	}

	/**
	 * 로그인 요청 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws CommonException 
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommonException {

		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message/message.jsp");

		if (memberId == null || memberId.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 0);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}
		if (memberPw == null || memberPw.trim().length() == 0) {
			MessageEntity messageEntity = new MessageEntity("validation", 1);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}
		memberId = memberId.trim();
		memberPw = memberPw.trim();
		MemberBiz biz = new MemberBiz();

		MemberDto dto = new MemberDto();
		dto.setMemberId(memberId);
		dto.setMemberPw(memberPw);

		System.out.println("memberId : [" + memberId + "]");
		System.out.println("memberPw : [" + memberPw + "]");
		biz.login(dto);
			if (dto.getGrade() != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("memberId", memberId);
				session.setAttribute("grade", dto.getGrade());
				session.setAttribute("dto", dto);
				
				MessageEntity messageEntity = new MessageEntity("success", 1);
				messageEntity.setLinkTitle("메인페이지");
				messageEntity.setUrl(CONTEXT_PATH +"/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 2);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH +"/member/frontController?action=loginForm");
				request.setAttribute("messageEntity", messageEntity);
			}
		 
		dispatcher.forward(request, response);
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
			if (session.getAttribute("memberId") != null) {
				session.removeAttribute("memberId");
			}
			if (session.getAttribute("grade") != null) {
				session.removeAttribute("grade");
			}
			session.invalidate();
		}
		String url = "/Cass/member/main.jsp";
		response.sendRedirect(url);
	}

	/**
	 * 회원가입 이동
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void memberInputForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/member/memberInput.jsp");
	}

	/**
	 * 회원가입 요청
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws CommonException
	 */
	protected void memberInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommonException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String addrCode = request.getParameter("addrCode");
		String memberAddr1 = request.getParameter("memberAddr1");
		String memberAddr2 = request.getParameter("memberAddr2");
		String memberAddr = addrCode + "/" + memberAddr1 + "/" + memberAddr2;
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String memberEmail = email1 + email2;
		String memberMobile = request.getParameter("memberMobile");
		String memberBirth = request.getParameter("memberBirth");
		String grade = "G";
		int point = 1000;
		String sex = request.getParameter("sex");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/message/message.jsp");

		System.out.println("memberId : [" + memberId + "]");
		System.out.println("memberPw : [" + memberPw + "]");
		System.out.println("memberAddr : [" + memberAddr + "]");
		System.out.println("memberMobile : [" + memberMobile + "]");
		System.out.println("memberEmail : [" + memberEmail + "]");
		System.out.println("memberBirth : [" + memberBirth + "]");
		System.out.println("grade : [" + grade + "]");
		System.out.println("point : [" + point + "]");
		System.out.println("sex : [" + sex + "]");

		if (memberId.isEmpty() || memberPw.isEmpty() || memberAddr.isEmpty() || memberEmail.isEmpty()
				|| memberBirth.isEmpty() || sex.isEmpty()) {
			MessageEntity messageEntity = new MessageEntity("error", 0);
			messageEntity.setLinkTitle("회원가입");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=memberInputForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}

		MemberDto dto = new MemberDto(memberId, memberPw, memberAddr, memberEmail, memberMobile, memberBirth, grade,
				point, sex);
		MemberBiz biz = new MemberBiz();

		try {
			biz.addMember(dto);
			MessageEntity messageEntity = new MessageEntity("success", 0);
			messageEntity.setLinkTitle("로그인하기");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
		} catch (CommonException e) {
			e.printStackTrace();
			MessageEntity messageEntity = e.getMessageEntity();
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=memberInputForm");
			messageEntity.setLinkTitle("회원가입 재시도");
			request.setAttribute("message", messageEntity);
		}
		dispatcher.forward(request, response);
	}

	/**
	 * 내 정보 조회
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws CommonException 
	 */
	protected void myInfoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, CommonException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("memberId") == null) {
			MessageEntity messageEntity = new MessageEntity("message", 0);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}
		String memberId = (String) session.getAttribute("memberId");
		MemberBiz biz = new MemberBiz();
		MemberDto dto = biz.myInfo(memberId);
		session.setAttribute("dto", dto);
		response.sendRedirect(CONTEXT_PATH + "/member/memberMyInfo.jsp");
	}

	/**
	 * 내 정보 변경 요청
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void myInfoUpdateSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("memberId") == null) {
			MessageEntity messageEntity = new MessageEntity("message", 0);
			messageEntity.setLinkTitle("로그인");
			messageEntity.setUrl(CONTEXT_PATH + "/mms04/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}

		String memberId = (String) session.getAttribute("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberAddr = request.getParameter("memberAddr");
		String memberEmail = request.getParameter("memberEmail");
		String memberMobile = request.getParameter("memberMobile");
		

		MemberBiz biz = new MemberBiz();
		MessageEntity messageEntity = null;
		try {
			biz.updateInfo(new MemberDto(memberId, memberPw, memberAddr, memberEmail, memberMobile));
			messageEntity = new MessageEntity("success", 2);
		} catch (CommonException e) {
			messageEntity = e.getMessageEntity();
		}
		System.out.println(memberId + ", " + memberPw + ", " + memberAddr + ", " + memberEmail + ", " + memberMobile);
		messageEntity.setLinkTitle("메인페이지");
		messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
		request.setAttribute("messageEntity", messageEntity);
		request.getRequestDispatcher("/message/message.jsp").forward(request, response);
	}

	/**
	 * 회원 정보(아이디,비밀번호 선택해서) 찾는 페이지
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/member/memberFind.jsp");
	}

	
	/**
	 * 아이디 찾기 선택
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findIdForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/member/memberFindId.jsp");
	}

	
	/**
	 * 비밀번호 찾기 선택
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPwForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(CONTEXT_PATH + "/member/memberFindPw.jsp");
	}

	
	
	protected void memberIdFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberBirth = request.getParameter("memberBirth");
		String memberMobile = request.getParameter("memberMobile");

		if (memberBirth.isEmpty() || memberMobile.isEmpty()) {
			MessageEntity messageEntity = new MessageEntity("validation", 4);
			messageEntity.setLinkTitle("아이디 찾기");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=findIdForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}

		MemberBiz biz = new MemberBiz();
		MemberDto dto = new MemberDto();
		dto.setMemberBirth(memberBirth);
		dto.setMemberMobile(memberMobile);

		biz.findId(dto);
		if (dto.getMemberId() != null) {
			MessageEntity messageEntity = new MessageEntity("success", 7);
			messageEntity.setLinkTitle("아이디 찾기 성공 : [" + dto.getMemberId() + "]");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		} else {
			MessageEntity messageEntity = new MessageEntity("error", 6);
			messageEntity.setLinkTitle("다시 아이디 찾기");
			messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=findIdForm");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			return;
		}
	}
	
	
	
	protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberBiz biz = new MemberBiz();
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		System.out.println(1);
		try {
			biz.memberList(list);
			request.setAttribute("list", list);
			request.getRequestDispatcher(CONTEXT_PATH + "/member/memberList.jsp").forward(request, response);
			System.out.println(2);
		} catch(CommonException e) {
			MessageEntity messageEntity = new MessageEntity("error", 4);
			messageEntity.setLinkTitle("메인으로");
			messageEntity.setUrl(CONTEXT_PATH +"/welcome.jsp");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
		
		
	}

}
