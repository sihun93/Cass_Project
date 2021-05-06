/**
 * 
 */
package com.work.model.biz;

import java.util.ArrayList;

import com.work.model.dao.CategoryDao;
import com.work.model.dto.MainCategoryDto;
import com.work.model.dto.ReviewDto;
import com.work.model.dto.SubCategoryDto;

/**
 * @author 박민주
 *
 */
public class CategoryBiz {
	private CategoryDao dao = CategoryDao.getInstance();
	
	/** 메인 카테고리 조회*/
	public ArrayList<MainCategoryDto> getMainCategory() {
		return dao.getMainCategory();
	}
	/** 서브 카테고리 조회*/
	public ArrayList<SubCategoryDto> getSubCategory() {
		return dao.getSubCategory();
	}
	/** 리뷰 조회*/
	public ArrayList<ReviewDto> getBestReviewList() {
		return dao.getBestReviewList();
	}

}
