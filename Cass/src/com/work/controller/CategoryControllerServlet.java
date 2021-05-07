package com.work.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.work.model.biz.CategoryBiz;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;

/**
 * @author 박민주
 * 시작 페이지에 카테고리와 후기 데이터를 가져오기 위한 서블릿
 */
@WebServlet(urlPatterns = { "/cass/categoryController" }, loadOnStartup = 1)
public class CategoryControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
		public ServletContext application;
		public String CONTEXT_PATH;
		
		public void init() {
			application = getServletContext();
			CONTEXT_PATH = application.getContextPath();
			application.setAttribute("CONTEXT_PATH", CONTEXT_PATH);
			
			CategoryBiz biz = new CategoryBiz();
			
			ArrayList<MainCategoryDto> mainCategoryList = biz.getMainCategory();
			ArrayList<SubCategoryDto> subCategoryList = biz.getSubCategory();
			application.setAttribute("mainCategoryList", mainCategoryList);
			application.setAttribute("subCategoryList", subCategoryList);
			
			ArrayList<ReviewDto> bestReviewList = biz.getBestReviewList();
			application.setAttribute("bestReviewList", bestReviewList);	
		}
}
