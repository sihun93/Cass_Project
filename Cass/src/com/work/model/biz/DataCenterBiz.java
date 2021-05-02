/**
 * 
 */
package com.work.model.biz;

import java.util.ArrayList;

import com.work.model.dao.DataCenterDao;
import com.work.model.dto.DataCenterDto;

/**
 * @author 백시훈
 *
 */
public class DataCenterBiz {
	private DataCenterDao dao = DataCenterDao.getInstance();
	public int getCountM(){
		return dao.getSexMSelecter();
	}
	public int getCount(){
		return dao.getCountTotal();
	}
}
