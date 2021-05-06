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
	        categoryCount.addProperty("애완용품", list1.get(0));
	        categoryCount.addProperty("관리", list1.get(1));
	        categoryCount.addProperty("교육", list1.get(2));
	        categoryCount.addProperty("사료", list1.get(3));
	        
	        JsonObject  select10age = new JsonObject ();
	        ArrayList<Integer> list2 = dao.getAge10Select();
	        select10age.addProperty("10대 애완용품", list2.get(0));
	        select10age.addProperty("10대 관리", list2.get(1));
	        select10age.addProperty("10대 교육", list2.get(2));
	        select10age.addProperty("10대 사료", list2.get(3));
	        
	        JsonObject  select20age = new JsonObject ();
	        ArrayList<Integer> list3 = dao.getAge20Select();
	        select20age.addProperty("20대 애완용품", list3.get(0));
	        select20age.addProperty("20대 관리", list3.get(1));
	        select20age.addProperty("20대 교육", list3.get(2));
	        select20age.addProperty("20대 사료", list3.get(3));
	        
	        JsonObject  select30age = new JsonObject ();
	        ArrayList<Integer> list4 = dao.getAge30Select();
	        select30age.addProperty("30대 애완용품", list4.get(0));
	        select30age.addProperty("30대 관리", list4.get(1));
	        select30age.addProperty("30대 교육", list4.get(2));
	        select30age.addProperty("30대 사료", list4.get(3));
	        
	        JsonObject  select40age = new JsonObject ();
	        ArrayList<Integer> list5 = dao.getAge40Select();
	        select40age.addProperty("40대 애완용품", list5.get(0));
	        select40age.addProperty("40대 관리", list5.get(1));
	        select40age.addProperty("40대 교육", list5.get(2));
	        select40age.addProperty("40대 사료", list5.get(3));
	        
	        JsonObject  select50age = new JsonObject ();
	        ArrayList<Integer> list6 = dao.getAge50Select();
	        select50age.addProperty("50대 애완용품", list6.get(0));
	        select50age.addProperty("50대 관리", list6.get(1));
	        select50age.addProperty("50대 교육", list6.get(2));
	        select50age.addProperty("50대 사료", list6.get(3));
	        
	        JsonObject  selectMcount = new JsonObject ();
	        ArrayList<Integer> list7 = dao.getSelectMCount();
	        selectMcount.addProperty("남성 애완용품", list7.get(0));
	        selectMcount.addProperty("남성 관리", list7.get(1));
	        selectMcount.addProperty("남성 교육", list7.get(2));
	        selectMcount.addProperty("남성 사료", list7.get(3));
	        
	        JsonObject  selectFcount = new JsonObject ();
	        ArrayList<Integer> list8 = dao.getSelectFCount();
	        selectFcount.addProperty("여성 애완용품", list8.get(0));
	        selectFcount.addProperty("여성 관리", list8.get(1));
	        selectFcount.addProperty("여성 교육", list8.get(2));
	        selectFcount.addProperty("여성 사료", list8.get(3));
	        
	        JsonArray infoArray = new JsonArray();
	        infoArray.add(sexCount);
	        infoArray.add(ageCount);
	        infoArray.add(categoryCount);
	        infoArray.add(selectMcount);
	        infoArray.add(selectFcount);
	        infoArray.add(select10age);
	        infoArray.add(select20age);
	        infoArray.add(select30age);
	        infoArray.add(select40age);
	        infoArray.add(select50age);
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
