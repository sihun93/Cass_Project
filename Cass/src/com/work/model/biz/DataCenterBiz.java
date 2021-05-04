/**
 * 
 */
package com.work.model.biz;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.work.model.dao.DataCenterDao;

/**
 * @author 백시훈
 *
 */
public class DataCenterBiz {
	private DataCenterDao dao = DataCenterDao.getInstance();

	/**
	 * 남성의 숫자를 구하는 메서드
	 * 
	 * @return
	 */
	public int getCountM() {
		return dao.getSexMSelecter();
	}

	/**
	 * 전체 인원수를 구하는 메서드
	 * 
	 * @return
	 */
	public int getCount() {
		return dao.getCountTotal();
	}

	/**
	 * 나이대별로 회원수를 구하는 메서드
	 * 
	 * @return
	 */
	public ArrayList<Integer> getAgeCount() {
		return dao.getAge();
	}

	/**
	 * 카테고리 조회수를 구하는 메거드
	 * 
	 * @return
	 */
	public ArrayList<Integer> getCategoryCount() {
		return dao.getCategoryCount();
	}

	/**
	 * 남성 인기 카테고리 조회수 구하는 메서드
	 * 
	 * @return
	 */
	public ArrayList<Integer> getSelectMCount() {
		return dao.getSelectMCount();
	}

	/**
	 * 여성 인기 카테고리 조회수 구하는 메서드
	 * 
	 * @return
	 */
	public ArrayList<Integer> getSelectFCount() {
		return dao.getSelectFCount();
	}

	/**
	 * 10대 인기카테고리 구하는 메서드
	 * 
	 * @return 10대 유저별 카테고리 조회수
	 */
	public ArrayList<Integer> get10AgeSelect() {
		return dao.getAge10Select();
	}

	/**
	 * 20대 인기카테고리 구하는 메서드
	 * 
	 * @return 20대 유저별 카테고리 조회수
	 */
	public ArrayList<Integer> get20AgeSelect() {
		return dao.getAge20Select();
	}

	/**
	 * 30대 인기카테고리 구하는 메서드
	 * 
	 * @return 30대 유저별 카테고리 조회수
	 */
	public ArrayList<Integer> get30AgeSelect() {
		return dao.getAge30Select();
	}

	/**
	 * 40대 인기카테고리 구하는 메서드
	 * 
	 * @return 40대 유저별 카테고리 조회수
	 */
	public ArrayList<Integer> get40AgeSelect() {
		return dao.getAge40Select();
	}

	/**
	 * 50대 인기카테고리 구하는 메서드
	 * 
	 * @return 50대 유저별 카테고리 조회수
	 */
	public ArrayList<Integer> get50AgeSelect() {
		return dao.getAge50Select();
	}

	/**
	 * json파일 만드는 메서드
	 */
	public void makeJson() {
		 FileWriter writer=null;
		 JsonObject  sexCount = new JsonObject ();
	        int m = getCountM();
	        int total = getCount();
	        int f = total - m;
	        sexCount.addProperty("male", m);
	        sexCount.addProperty("female", f);

	        JsonObject  ageCount = new JsonObject ();
	        ArrayList<Integer> list = dao.getAge();
	        ageCount.addProperty("10대", list.get(0));
	        ageCount.addProperty("20대", list.get(1));
	        ageCount.addProperty("30대", list.get(2));
	        ageCount.addProperty("40대", list.get(3));
	        ageCount.addProperty("50대", list.get(4));
	        
	        JsonObject  categoryCount = new JsonObject ();
	        ArrayList<Integer> list1 = dao.getCategoryCount();
	        categoryCount.addProperty("카테고리1", list1.get(0));
	        categoryCount.addProperty("카테고리2", list1.get(1));
	        categoryCount.addProperty("카테고리3", list1.get(2));
	        categoryCount.addProperty("카테고리4", list1.get(3));
	        
	        
	        JsonArray infoArray = new JsonArray();
	        infoArray.add(sexCount);
	        infoArray.add(ageCount);
	        infoArray.add(categoryCount);
	        JsonObject jsonobject = new JsonObject();
	        jsonobject.add("DataCenter_JsonFile", infoArray);
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(jsonobject);

		try {
		    writer = new FileWriter("C:/student_ucamp33/workspace_servlet/DataCenter.json");
		    writer.write(json);
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


	}
}
