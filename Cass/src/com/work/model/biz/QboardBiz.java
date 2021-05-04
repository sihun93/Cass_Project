/**
 * 
 */
package com.work.model.biz;

import java.util.ArrayList;

import com.work.model.dao.QboardDao;
import com.work.model.dto.QboardDto;

/**
 * @author 박민주
 *
 */
public class QboardBiz {
	private QboardDao dao = QboardDao.getInstance();
	public  ArrayList<QboardDto> getQboardList() {
		return dao.getQboardList();
	}
	
	public  ArrayList<QboardDto> getQboardDetail(String qboardNum) {
		return dao.getQboardDetail(qboardNum);
	}
}
