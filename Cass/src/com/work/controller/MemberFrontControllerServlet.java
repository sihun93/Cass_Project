package com.work.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.work.model.biz.MemberBiz;
import com.work.model.dao.CommonException;
import com.work.model.dto.MemberDto;
import com.work.model.dto.MessageEntity;
import com.work.util.Utility;

/**
 * Servlet implementation class MemberFrontControllerServlet
 */
@WebServlet(urlPatterns = { "/member/frontController"})
public class MemberFrontControllerServlet extends HttpServlet {
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
			myInfoForm(request, response);
			break;
		case "myInfoUpdateSave":
			try {
				myInfoUpdateSave(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
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
		case "memberDelete":
			try {
				memberDelete(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "pointModifyForm":
			pointModifyForm(request, response);
			break;
		case "pointModify":
			pointModify(request, response);
			break;
		case "pointUpdate":
			pointUpdate(request, response);
			break;
		case "memberIdFind":
			memberIdFind(request, response);
			break;
		case "memberPwFind":
			memberPwFind(request, response);
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
		 * 로그인페이지로 이동하는 Form
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void loginForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/login.jsp");
			dispatcher.forward(request, response);
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
				session.setAttribute("dto", dto);
				MessageEntity messageEntity = new MessageEntity("success", 1);
				messageEntity.setLinkTitle("메인으로");
				messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 2);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
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
				if (session.getAttribute("dto") != null) {
					session.removeAttribute("dto");
				}
				session.invalidate();
			}
			MessageEntity messageEntity = new MessageEntity("success", 4);
			messageEntity.setLinkTitle("메인으로");
			messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
			request.setAttribute("messageEntity", messageEntity);
			request.getRequestDispatcher("/message/message.jsp").forward(request, response);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberInput.jsp");
			dispatcher.forward(request, response);
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

			MemberDto chkDto = new MemberDto();
			try {
				chkDto.setMemberId(memberId);
				biz.myInfo(chkDto);
				if (chkDto.getMemberPw() != null) { // 가입된 아이디인 경우

					MessageEntity messageEntity = new MessageEntity("validation", 8);
					messageEntity.setLinkTitle("회원가입 재시도");
					messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=memberInputForm");
					request.setAttribute("messageEntity", messageEntity);
					request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				} else {
					biz.addMember(dto);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/member/login.jsp");
					dispatcher.forward(request, response);
				}
			} catch (CommonException e) {
				e.printStackTrace();
				MessageEntity messageEntity = new MessageEntity("error", 0);
				messageEntity.setLinkTitle("회원가입 재시도");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=memberInputForm");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
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
				throws ServletException, IOException {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("dto") == null) {
				MessageEntity messageEntity = new MessageEntity("message", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			String memberId = ((MemberDto)session.getAttribute("dto")).getMemberId();
			
			MemberBiz biz = new MemberBiz();
			MemberDto dto = new MemberDto();
			dto.setMemberId(memberId);
			try {
				biz.myInfo(dto);
				session.setAttribute("dto", dto);
				request.getRequestDispatcher("/member/memberMyInfo.jsp").forward(request, response);
			} catch (CommonException e) {
				e.printStackTrace();
				MessageEntity messageEntity = e.getMessageEntity();
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
				messageEntity.setLinkTitle("로그인");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
			
		}

		/**
		 * 내 정보 변경 요청
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws CommonException 
		 */
		protected void myInfoUpdateSave(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException, CommonException {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("dto") == null) {
				MessageEntity messageEntity = new MessageEntity("message", 0);
				messageEntity.setLinkTitle("로그인");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			String memberId = ((MemberDto)session.getAttribute("dto")).getMemberId();
			String memberPw = request.getParameter("memberPw");
			String addrCode = request.getParameter("addrCode");
			String memberAddr1 = request.getParameter("memberAddr1");
			String memberAddr2 = request.getParameter("memberAddr2");
			String memberAddr = addrCode + "/" + memberAddr1 + "/" + memberAddr2;
			String memberEmail = request.getParameter("memberEmail");
			String memberMobile = request.getParameter("memberMobile");
			int point = Integer.parseInt(request.getParameter("point"));

			MemberBiz biz = new MemberBiz();
			MemberDto dto = new MemberDto();
			dto.setMemberId(memberId);
			dto.setMemberPw(memberPw);
			dto.setMemberAddr(memberAddr);
			dto.setMemberEmail(memberEmail);
			dto.setMemberMobile(memberMobile);
			dto.setPoint(point);
			try {
				biz.updateInfo(dto);
				session.setAttribute("dto", dto);
				System.out.println(memberId+"/"+memberPw+"/"+memberAddr+"/"+memberEmail+"/"+memberMobile+"/"+point);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
				dispatcher.forward(request, response);
			} catch (CommonException e) {
				e.printStackTrace();
				MessageEntity messageEntity = e.getMessageEntity();
				messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
				messageEntity.setLinkTitle("메인으로");
				request.setAttribute("message", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
			
		}
		
		/**
		 * 포인트 변경 페이지 FORM
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void pointModifyForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/pointModifyForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		/**
		 * 회원 포인트수정 페이지
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void pointModify(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String memberId = request.getParameter("memberId");
			if (memberId.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 0);
				messageEntity.setLinkTitle("포인트 수정");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=pointModifyForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			memberId = memberId.trim();
			MemberBiz biz = new MemberBiz();
			MemberDto dto = biz.getPoint(memberId);
			if (dto != null) {
				System.out.println(memberId);
				request.setAttribute("dto", dto);
				RequestDispatcher requestView = request.getRequestDispatcher("/member/pointModify.jsp");
				requestView.forward(request, response);
				return;
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 16);
				messageEntity.setLinkTitle("포인트 수정하기");
				messageEntity.setUrl(CONTEXT_PATH +"/member/frontController?action=pointModifyForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
		}
		
		
		/**
		 * 포인트 수정 요청
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void pointUpdate(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			System.out.println("[pointUpdate] START");
			
			String memberId = request.getParameter("memberId");
			String sPoint = request.getParameter("point");
			
			System.out.println("memberId : ["+memberId+"]");
			System.out.println("sPoint : ["+sPoint+"]");
			
			if (memberId.isEmpty()) {
				System.out.println(">>> memberId.isEmpty() error ");
				MessageEntity messageEntity = new MessageEntity("error", 16);
				messageEntity.setLinkTitle("포인트 수정");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=pointModifyForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			if (sPoint.isEmpty() || !(Utility.isNumber(sPoint))) {
				System.out.println(">>> sPoint.isEmpty() || !(Utility.isNumber(sPoint)) error ");
				MessageEntity messageEntity = new MessageEntity("error", 16);
				messageEntity.setLinkTitle("포인트 수정");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=pointModifyForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			
			int point = Integer.parseInt(sPoint);
			MemberBiz biz = new MemberBiz();
			MemberDto dto = new MemberDto();
			dto.setMemberId(memberId);
			dto.setPoint(point);
			
			try {
				biz.pointModify(dto);
				MessageEntity messageEntity = new MessageEntity("success", 9);
				messageEntity.setLinkTitle("메인으로");
				messageEntity.setUrl(CONTEXT_PATH + "/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}catch(Exception e) {
				e.getStackTrace();
				System.out.println(">>> pointModify error ");
				MessageEntity messageEntity = new MessageEntity("error", 16);
				messageEntity.setLinkTitle("포인트 수정");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=pointModifyForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberFind.jsp");
			dispatcher.forward(request, response);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberFindId.jsp");
			dispatcher.forward(request, response);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/memberFindPw.jsp");
			dispatcher.forward(request, response);
		}

		
		
		
		/**
		 * 아이디 찾기 요청
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
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
			String memberId = biz.findId(memberBirth, memberMobile);
			if (memberId != null) {
				MessageEntity messageEntity = new MessageEntity("success", 7);
				messageEntity.setLinkTitle("아이디 찾기 성공 : [" + memberId + "] 클릭시 로그인창으로 이동됩니다.");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=loginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			} else {
				MessageEntity messageEntity = new MessageEntity("error", 13);
				messageEntity.setLinkTitle("다시 아이디 찾기");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=findIdForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
		}
		
		/**
		 * 비밀번호 찾기 요청
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void memberPwFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String memberId = request.getParameter("memberId");
			String memberMobile = request.getParameter("memberMobile");
			
			if(memberId.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 5);
				messageEntity.setLinkTitle("비밀번호조회");
				messageEntity.setUrl(CONTEXT_PATH +"/member/frontController?action=findForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			} else if(memberMobile.isEmpty()) {
				MessageEntity messageEntity = new MessageEntity("validation", 5);
				messageEntity.setLinkTitle("비밀번호조회");
				messageEntity.setUrl(CONTEXT_PATH +"/member/frontController?action=findForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
			memberId = memberId.trim();
			memberMobile = memberMobile.trim();
			
			MemberBiz biz = new MemberBiz();
			String memberPw = biz.findPw(memberId, memberMobile);
			System.out.println(memberId + "/" +memberMobile);
			if(memberPw != null) {
				MessageEntity messageEntity = new MessageEntity("success", 8);
				messageEntity.setLinkTitle("임시 비밀번호 발급 : [ "+memberPw+" ] 클릭시 로그인창으로 이동됩니다.");
				messageEntity.setUrl(CONTEXT_PATH +"/member/frontController?action=loginForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				System.out.println(memberPw);
				return;
			}else {
				MessageEntity messageEntity = new MessageEntity("error", 14);
				messageEntity.setLinkTitle("다시 비밀번호 찾기");
				messageEntity.setUrl(CONTEXT_PATH + "/member/frontController?action=findPwForm");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
				return;
			}
		}
		
		
		/**
		 * 회원전체조회
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			MemberBiz biz = new MemberBiz();
			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
			try {
				biz.memberList(list);
				request.setAttribute("list", list);
				request.getRequestDispatcher("memberList.jsp").forward(request, response);
			} catch(Exception e) {
				MessageEntity messageEntity = new MessageEntity("error", 4);
				messageEntity.setLinkTitle("메인으로");
				messageEntity.setUrl(CONTEXT_PATH +"/welcome.jsp");
				request.setAttribute("messageEntity", messageEntity);
				request.getRequestDispatcher("/message/message.jsp").forward(request, response);
			}
		}
		
		/**
		 * 회원탈퇴
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 * @throws CommonException
		 */
		protected void memberDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommonException {

			String memberId = request.getParameter("memberId");
			
			memberId = memberId.trim();
			
			MemberBiz biz = new MemberBiz();
			int rowCnt = biz.deleteMember(memberId);
			if(rowCnt > 0) {
				ArrayList<MemberDto> list = new ArrayList<MemberDto>();
				biz.memberList(list);
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
